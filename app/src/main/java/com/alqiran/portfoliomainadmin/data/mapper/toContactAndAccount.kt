package com.alqiran.portfoliomainadmin.data.mapper

import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.ContactAndAccounts
import com.alqiran.portfoliomainadmin.ui.model.ContactAndAccountsUiModel

fun ContactAndAccountsUiModel.toContactAndAccount(): ContactAndAccounts {
    return ContactAndAccounts(
            id = this.id,
            webName = this.webName,
            url = this.url
        )
}