package com.example.crud_project.domain.usecases.employees

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.crud_project.daoData.Repository

class GetOneEmployeeUseCase {

    @RequiresApi(Build.VERSION_CODES.N)
    operator fun invoke(id : String) =
        Repository.getInstance().getEmployee(id)

}