package com.example.todolist

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.todolist.placeholder.PlaceholderContent
import androidx.recyclerview.widget.*

/**
 * A fragment representing a list of Items.
 */
class todolistFragment : Fragment() {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }

                view.addItemDecoration(
                    DividerItemDecoration(
                        view.getContext(),
                        DividerItemDecoration.VERTICAL
                    )
                )

                adapter = MytodolistRecyclerViewAdapter(PlaceholderContent.ITEMS, clickListener {
                    val bundle = bundleOf("taskId" to it.id)
                    findNavController().navigate(
                        R.id.action_todolistFragment_to_FirstFragment,
                        bundle
                    )
                })
                val mIth = ItemTouchHelper(object :
                    ItemTouchHelper.SimpleCallback(
                        0,
                        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                    ) {
                    override fun onMove(
                        recyclerView: RecyclerView,
                        viewHolder: RecyclerView.ViewHolder,
                        target: RecyclerView.ViewHolder
                    ): Boolean {
                        return false
                    }

                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                        val adapter = adapter
                        viewHolder as MytodolistRecyclerViewAdapter.ViewHolder
                        val position = viewHolder.bindingAdapterPosition

                        when (direction) {
                            ItemTouchHelper.LEFT -> {
                                adapter?.notifyItemRemoved(position);
                                PlaceholderContent.removeTask(PlaceholderContent.ITEMS[position].id)
                            }
                            ItemTouchHelper.RIGHT -> {
                                PlaceholderContent.ITEMS[position].complete =
                                    !PlaceholderContent.ITEMS[position].complete
                                adapter?.notifyItemChanged(position)
                            }
                        }
                    }
                })
                mIth.attachToRecyclerView(view)
            }
        }
        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            todolistFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}