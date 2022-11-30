package com.example.recyclerdetail.domain.usecases.employees

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.recyclerdetail.data.Repository

class GetEmployeeByIdUseCase {

    @RequiresApi(Build.VERSION_CODES.N)
    operator fun invoke(id: String) =
        Repository.getEmployeeById(id)
}