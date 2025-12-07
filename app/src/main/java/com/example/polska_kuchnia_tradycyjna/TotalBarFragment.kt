package com.example.polska_kuchnia_tradycyjna

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.polska_kuchnia_tradycyjna.databinding.FragmentTotalBarBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TotalBarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TotalBarFragment : Fragment() {
    private var _binding: FragmentTotalBarBinding? = null
    private val binding get() = _binding!!

    private var param1: String? = null
    private var param2: String? = null

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTotalBarBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Cart.currentOrder.observe(viewLifecycleOwner) {
            binding.totalBarCurrentMealPrice.text = "Cena tego posiłku: ${it.price} zł"
        }

        Cart.orders.observe(viewLifecycleOwner) {
            binding.totalBarTotalPrice.text = "Razem: ${Cart.getTotalPrice()} zł"
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TotalBarFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TotalBarFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}