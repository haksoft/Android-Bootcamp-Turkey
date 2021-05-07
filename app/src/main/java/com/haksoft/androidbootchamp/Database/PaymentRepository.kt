package com.haksoft.androidbootchamp.Database

import androidx.lifecycle.LiveData
import com.haksoft.androidbootchamp.Database.Payment
import com.haksoft.androidbootchamp.Database.PaymentDAO

class PaymentRepository(private val paymentDAO: PaymentDAO) {


    val readAllData : LiveData<List<Payment>> = paymentDAO.readAllData()



    suspend fun addPayment(payment: Payment){
         return paymentDAO.addPayment(payment)
    }
    suspend fun deletePayment(payment: Payment){
        paymentDAO.deletePayment(payment)
    }
    suspend fun changingAmount(newAmount: Double, currencyid : Int){
        paymentDAO.changingAmount(newAmount,currencyid)
    }
    suspend fun changingAmountType(newAmountType: String, currencyid : Int){
        paymentDAO.changingAmountType(newAmountType,currencyid)
    }
}