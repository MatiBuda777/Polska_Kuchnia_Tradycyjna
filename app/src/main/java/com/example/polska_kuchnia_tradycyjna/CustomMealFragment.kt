package com.example.polska_kuchnia_tradycyjna

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.example.polska_kuchnia_tradycyjna.databinding.FragmentCustomMealBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CustomMealFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CustomMealFragment : Fragment() {
    private var _binding: FragmentCustomMealBinding? = null
    private val binding get() = _binding!!
    private val _soups = listOf("Barszcz", "Ogórkowa", "Pomidorowa", "Rosół", "Żurek")
    private val _mainCourses = listOf("Bigos", "Kotlet mielony", "Kotlet schabowy", "Gołąbki", "Pierogi")
    private val _drinks = listOf("Herbata", "Kawa", "Kompot Owocowy")

    private val _soupPrices = mapOf(
        "Barszcz" to 9,
        "Ogórkowa" to 8,
        "Pomidorowa" to 7,
        "Rosół" to 8,
        "Żurek" to 10
    )

    private val _mainPrices = mapOf(
        "Bigos" to 15,
        "Kotlet mielony" to 14,
        "Kotlet schabowy" to 16,
        "Gołąbki" to 13,
        "Pierogi" to 12
    )

    private val _drinkPrices = mapOf(
        "Herbata" to 4,
        "Kawa" to 6,
        "Kompot Owocowy" to 3
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
    ): View {
        _binding = FragmentCustomMealBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Adaptery
        val adapterSoups = ArrayAdapter( // Adapter zupy
            requireContext(),
            android.R.layout.simple_spinner_item,
            _soups
        )
        adapterSoups.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerSelectSoup.adapter = adapterSoups

        val adapterMainCourses = ArrayAdapter( // Adapter drugiego dania
            requireContext(),
            android.R.layout.simple_spinner_item,
            _mainCourses
        )
        adapterMainCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerSelectMainCourse.adapter = adapterMainCourses

        val adapterDrinks = ArrayAdapter( // Adapter napoju
            requireContext(),
            android.R.layout.simple_spinner_item,
            _drinks
        )
        adapterDrinks.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerSelectDrink.adapter = adapterDrinks


        // Nasłuchiwacze
        var selectedSoup = ""
        var selectedDish = ""
        var selectedDrink = ""
        var meal: Meal? = null

        fun updateMeal(){ // Aktualizacja skomponowanego posiłku
            val price = (_soupPrices[selectedSoup] ?: 0) +
                    (_mainPrices[selectedDish] ?: 0) +
                    (_drinkPrices[selectedDrink] ?: 0)

            meal = Meal(
                selectedSoup, selectedDish, selectedDrink,
                soupRes = null, mainRes = null, drinkRes = null,
                price
            )
            Cart.setCurrentOrder(meal)
        }


        binding.spinnerSelectSoup.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
            {
                selectedSoup = parent?.getItemAtPosition(position).toString()
                updateMeal()
                when (selectedSoup) {
                    "Barszcz" -> binding.imageviewSoup.setImageResource(R.drawable.barszcz)
                    "Ogórkowa" -> binding.imageviewSoup.setImageResource(R.drawable.ogorkowa)
                    "Pomidorowa" -> binding.imageviewSoup.setImageResource(R.drawable.pomidorowa)
                    "Rosół" -> binding.imageviewSoup.setImageResource(R.drawable.rosol)
                    "Żurek" -> binding.imageviewSoup.setImageResource(R.drawable.zurek)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("No Soup Selected")
            }
        }

        binding.spinnerSelectMainCourse.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
            {
                selectedDish = parent?.getItemAtPosition(position).toString()
                updateMeal()
                when (selectedDish) {
                    "Bigos" -> binding.imageviewMainCourse.setImageResource(R.drawable.bigos)
                    "Kotlet mielony" -> binding.imageviewMainCourse.setImageResource(R.drawable.kotlety_mielone)
                    "Kotlet schabowy" -> binding.imageviewMainCourse.setImageResource(R.drawable.kotlet_schabowy)
                    "Gołąbki" -> binding.imageviewMainCourse.setImageResource(R.drawable.golabki)
                    "Pierogi" -> binding.imageviewMainCourse.setImageResource(R.drawable.pierogi)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("No Main Course Selected")
            }
        }



        binding.spinnerSelectDrink.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
            {
                selectedDrink = parent?.getItemAtPosition(position).toString()
                updateMeal()
                when (selectedDrink) {
                    "Herbata" -> binding.imageviewDrinks.setImageResource(R.drawable.herbata)
                    "Kawa" -> binding.imageviewDrinks.setImageResource(R.drawable.kawa)
                    "Kompot Owocowy" -> binding.imageviewDrinks.setImageResource(R.drawable.kompot)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("No Drink Selected")
            }
        }

        binding.buttonConfirmOrder.setOnClickListener {
            if (meal !== null) Cart.addMeal(meal)
            findNavController().navigate(R.id.action_customMealFragment_to_summaryFragment)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CustomMealFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CustomMealFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}