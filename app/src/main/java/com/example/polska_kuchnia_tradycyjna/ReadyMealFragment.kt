package com.example.polska_kuchnia_tradycyjna

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import androidx.navigation.fragment.findNavController
import com.example.polska_kuchnia_tradycyjna.databinding.FragmentMenuChoiceBinding
import com.example.polska_kuchnia_tradycyjna.databinding.FragmentReadyMealBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ReadyMealFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReadyMealFragment : Fragment() {
    private var _binding: FragmentReadyMealBinding? = null
    private val binding get() = _binding!!

    private val _meals = listOf(
        Meal("Barszcz, Bigos, Herbata", R.drawable.barszcz, R.drawable.bigos, R.drawable.herbata, 27),
        Meal("Ogórkowa, Kotlet mielony, Kawa", R.drawable.ogorkowa, R.drawable.kotlety_mielone, R.drawable.kawa, 29),
        Meal("Pomidorowa, Kotlet schabowy, Kompot", R.drawable.pomidorowa, R.drawable.kotlet_schabowy, R.drawable.kompot, 25),
        Meal("Rosół, Gołąbki, Herbata", R.drawable.rosol, R.drawable.golabki, R.drawable.herbata, 26),
        Meal("Żurek, Pierogi, Kawa", R.drawable.zurek, R.drawable.pierogi, R.drawable.kawa, 29)
    )


    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReadyMealBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // wypełnienie RadioGroup opcjami
        _meals.forEach { meal ->
            val radioButton = RadioButton(requireContext()).apply {
                text = meal.name
                id = View.generateViewId()
            }
            binding.radioGroupMeals.addView(radioButton)
        }

        // nasłuchiwacz
        var selectedMeal: Meal? = null

        binding.radioGroupMeals.setOnCheckedChangeListener { _, checkedId ->
            val checked = view.findViewById<RadioButton>(checkedId)
            selectedMeal = _meals.find { it.name == checked.text }

            selectedMeal?.let {
                binding.imageviewSoup.setImageResource(it.soupRes)
                binding.imageviewMainCourse.setImageResource(it.mainRes)
                binding.imageviewDrinks.setImageResource(it.drinkRes)
            }
        }


        // Guzik
            binding.buttonConfirmOrder.setOnClickListener {
                selectedMeal?.let { cart.addMeal(it) }
                findNavController().navigate(R.id.action_readyMealFragment_to_summaryFragment)
            }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ReadyMealFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ReadyMealFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}