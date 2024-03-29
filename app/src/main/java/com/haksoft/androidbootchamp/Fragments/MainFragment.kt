package com.haksoft.androidbootchamp.Fragments


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.haksoft.androidbootchamp.Database.Payment
import com.haksoft.androidbootchamp.ViewModel.PaymentViewModel
import com.haksoft.androidbootchamp.R
import com.haksoft.androidbootchamp.RecyclerView.Adapter
import com.haksoft.androidbootchamp.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding : FragmentMainBinding
    private lateinit var mPaymentViewModel: PaymentViewModel
    private lateinit var paymentList :List<Payment>
    private lateinit var preferences : SharedPreferences
    var sum = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainBinding.inflate(inflater,container,false)
        val cardViewToChangingUserFragment = binding.cardView
        val buttonEkle = binding.buttonEkle
        val nameText = binding.textName
        val chipGroup = binding.ChipGroup
        preferences = requireActivity().getSharedPreferences("com.haksoft.androidbootchamp", Context.MODE_PRIVATE)
        var adapter = Adapter()
        var recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 1)
        mPaymentViewModel = ViewModelProvider(this).get(PaymentViewModel::class.java)
        mPaymentViewModel.readAllData.observe(viewLifecycleOwner, Observer { payment ->

            paymentList = payment
            val radioButtonPara = preferences?.getInt("radioButtonPara", 0)
            for (i in paymentList) {
                sum += i.amount
            }

            when (radioButtonPara) {
                0 -> binding.textHarcamaTutar.text = sum.toInt().toString() + "TL"
                1 -> binding.textHarcamaTutar.text = sum.toInt().toString() + "$"
                2 -> binding.textHarcamaTutar.text = sum.toInt().toString() + "£"
                3 -> binding.textHarcamaTutar.text = sum.toInt().toString() + "€"
            }


            sum = 0.0

            adapter.setData(payment)

        })


        mPaymentViewModel.getConverted("TRY")
        mPaymentViewModel.myResponse.observe(viewLifecycleOwner, Observer { currency ->
            val USD = currency.conversion_rates.USD
            val EUR = currency.conversion_rates.EUR
            val GBP = currency.conversion_rates.GBP
            if(::paymentList.isInitialized) {
                if (!paymentList.isEmpty()) {
                    for (payment in paymentList) {
                        val radioButtonPara = preferences?.getInt("radioButtonPara", 0)
                        val amountType = payment.amountType
                        when (radioButtonPara) {
                            0 -> {

                                if (!amountType.equals("TL")) {
                                    val paymentID = payment.id
                                    if(payment.amountType.equals("$")){
                                        val toTL = payment.amount/USD
                                        payment.amount = toTL
                                        payment.amountType = "TL"
                                    }
                                    if(payment.amountType.equals("£")){
                                        val toTL = payment.amount/GBP
                                        payment.amount = toTL
                                        payment.amountType = "TL"
                                    }
                                    if(payment.amountType.equals("€")){
                                        val toTL = payment.amount/EUR
                                        payment.amount = toTL
                                        payment.amountType = "TL"
                                    }
                                    mPaymentViewModel.changingAmount(payment.amount, paymentID)
                                    payment.amountType = "TL"
                                    mPaymentViewModel.changingAmountType(payment.amountType, paymentID)
                                }
                            }
                            1 -> {


                                if (!amountType.equals("$")) {
                                    val paymentID = payment.id
                                    if (!amountType.equals("TL")) {
                                        if(payment.amountType.equals("$")){
                                            val toTL = payment.amount/USD
                                            payment.amount = toTL
                                            payment.amountType = "TL"
                                        }
                                        if(payment.amountType.equals("£")){
                                            val toTL = payment.amount/GBP
                                            payment.amount = toTL
                                            payment.amountType = "TL"
                                        }
                                        if(payment.amountType.equals("€")){
                                            val toTL = payment.amount/EUR
                                            payment.amount = toTL
                                            payment.amountType = "TL"
                                        }
                                    }
                                    val tlToUsd = payment.amount * USD
                                    mPaymentViewModel.changingAmount(tlToUsd, paymentID)
                                    payment.amountType = "$"
                                    mPaymentViewModel.changingAmountType(payment.amountType, paymentID)

                                }
                            }
                            2 -> {


                                if (!amountType.equals("£")) {
                                    val paymentID = payment.id
                                    if (!amountType.equals("TL")) {
                                        if(payment.amountType.equals("$")){
                                            val toTL = payment.amount/USD
                                            payment.amount = toTL
                                            payment.amountType = "TL"
                                        }
                                        if(payment.amountType.equals("£")){
                                            val toTL = payment.amount/GBP
                                            payment.amount = toTL
                                            payment.amountType = "TL"
                                        }
                                        if(payment.amountType.equals("€")){
                                            val toTL = payment.amount/EUR
                                            payment.amount = toTL
                                            payment.amountType = "TL"
                                        }
                                    }
                                    val tlToGbp = payment.amount * GBP
                                    mPaymentViewModel.changingAmount(tlToGbp, paymentID)
                                    payment.amountType = "£"
                                    mPaymentViewModel.changingAmountType(payment.amountType, paymentID)

                                }
                            }
                            3 -> {


                                if (!amountType.equals("€")) {
                                    val paymentID = payment.id
                                    if (!amountType.equals("TL")) {
                                        if(payment.amountType.equals("$")){
                                            val toTL = payment.amount/USD
                                            payment.amount = toTL
                                            payment.amountType = "TL"
                                        }
                                        if(payment.amountType.equals("£")){
                                            val toTL = payment.amount/GBP
                                            payment.amount = toTL
                                            payment.amountType = "TL"
                                        }
                                        if(payment.amountType.equals("€")){
                                            val toTL = payment.amount/EUR
                                            payment.amount = toTL
                                            payment.amountType = "TL"
                                        }
                                    }
                                    val tlToEur = payment.amount * EUR
                                    mPaymentViewModel.changingAmount(tlToEur, paymentID)
                                    payment.amountType = "€"
                                    mPaymentViewModel.changingAmountType(payment.amountType, paymentID)
                                }
                            }
                        }

                    }
                }
            }
        })

        chipGroup.setOnCheckedChangeListener { group, checkedId ->
   if(checkedId >0){
  //     Toast.makeText(context, "$checkedId", Toast.LENGTH_SHORT).show()

       val chip : View = binding.ChipGroup.findViewById(checkedId)
       val index = binding.ChipGroup.indexOfChild(chip)
       when(index){
           0 -> {

               preferences?.edit()?.putInt("radioButtonPara", 0)?.apply()
               mPaymentViewModel.getConverted("TRY")
           }
           1 -> {

               preferences?.edit()?.putInt("radioButtonPara", 1)?.apply()
               mPaymentViewModel.getConverted("TRY")
           }
           2 -> {

               preferences?.edit()?.putInt("radioButtonPara",2)?.apply()
               mPaymentViewModel.getConverted("TRY")
           }
           3 -> {

               preferences?.edit()?.putInt("radioButtonPara", 3)?.apply()
               mPaymentViewModel.getConverted("TRY")
           }

       }
   }

        }

        mPaymentViewModel.changeTheUser(preferences, nameText)


        cardViewToChangingUserFragment.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_changeUserFragment)

        }
        buttonEkle.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_uploadFragment)
        }
        return  binding.root


    }




}