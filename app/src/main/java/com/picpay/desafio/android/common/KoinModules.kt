package com.picpay.desafio.android.common

import com.picpay.desafio.android.data.di.DataDI
import com.picpay.desafio.android.domain.di.DomainDI
import com.picpay.desafio.android.presentation.di.PresentationDI
import com.picpay.desafio.core.data.database.di.DatabaseDI
import com.picpay.desafio.core.data.network.di.NetworkDI
import com.picpay.desafio.core.data.utils.di.DispatcherDI

fun getKoinModules() =
    NetworkDI.module +
            DatabaseDI.module +
            DispatcherDI.module +
            DataDI.module +
            DomainDI.module +
            PresentationDI.module