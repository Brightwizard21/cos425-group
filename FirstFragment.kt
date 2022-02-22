package com.example.todolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.todolist.databinding.FragmentFirstBinding
import com.example.todolist.placeholder.PlaceholderContent
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private lateinit var task: Task
    private var taskId: Int = -1

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        val taskId = arguments?.getInt("taskId")
        if (taskId != null) {
            this.taskId = taskId

            val task = PlaceholderContent.getTask(taskId)
            if (task != null) {
                this.task = task

                binding.txtTask.setText(task.content)
                binding.EditDue.setText(task.duedate.toString())
                binding.checkBoxComplete.isChecked = task.complete
                binding.EditNote.setText(task.details)
            }
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btCancel.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btSave.setOnClickListener {
            task.content = binding.txtTask.text.toString()
            task.complete = binding.checkBoxComplete.isChecked
            task.details = binding.EditNote.text.toString()
            task.duedate = Date(binding.EditDue.text.toString())
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}