package com.alqiran.portfoliomainadmin.data.mapper

import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.ContactAndAccounts
import com.alqiran.portfoliomainadmin.ui.model.ContactAndAccountsUiModel

fun List<ContactAndAccountsUiModel>.toContactAndAccounts(): List<ContactAndAccounts> {
    return this.map { item ->
        ContactAndAccounts(
            id = item.id,
            webName = item.webName,
            url = item.url
        )
    }
}