package com.jonasrosendo.desafiousemobile.data.api

import com.jonasrosendo.desafiousemobile.data.mocked.MockApiService
import com.jonasrosendo.desafiousemobile.data.model.User
import com.jonasrosendo.desafiousemobile.data.model.UserResponse
import com.jonasrosendo.desafiousemobile.di.DaggerUseMobileComponent
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import javax.inject.Inject

@RunWith(JUnit4::class)
class UseMobileServiceTest {

    private lateinit var api: UseMobileApi
    private val userAmanda = User(
        1,
        "Amanda Campos",
        "amanda@gmail.com",
        "https://lh3.googleusercontent.com/proxy/bvYesA4SRTF0FC0UC2uDX0whxjjfDWJEqMmd188TXqqr_fGfa_e5f7DNc5gLCmfYNCHaPB1vlkmoszpoUvkY7A",
        true
    )
    private val userFernanda = User(
        2,
        "Fernanda Almeida",
        "fernanda@gmail.com",
        "https://www.w3schools.com/w3images/avatar6.png",
        false
    )

    @Before
    fun setup() {
        api = MockApiService
    }

    @Test
    fun `when api is called return two users`() {
        runBlocking {
            val users = api.getUsers().response
            assertEquals(2, users.size)
        }
    }

    @Test
    fun `when api is called check if returned user informations are correct`() {
        runBlocking {
            val users = api.getUsers().response

            assertEquals(users[0].id, userAmanda.id)
            assertEquals(users[0].name, userAmanda.name)
            assertEquals(users[0].email, userAmanda.email)
            assertEquals(users[0].photo, userAmanda.photo)
            assertEquals(users[0].isVerified, userAmanda.isVerified)

            assertEquals(users[1].id, userFernanda.id)
            assertEquals(users[1].name, userFernanda.name)
            assertEquals(users[1].email, userFernanda.email)
            assertEquals(users[1].photo, userFernanda.photo)
            assertEquals(users[1].isVerified, userFernanda.isVerified)
        }
    }

    @Test
    fun `when searching user by id if user exists check the given informations`() {
        runBlocking {
            val user = api.getUserById(2)
            assertEquals(user?.id, userFernanda.id)
            assertEquals(user?.name, userFernanda.name)
            assertEquals(user?.email, userFernanda.email)
            assertEquals(user?.photo, userFernanda.photo)
            assertEquals(user?.isVerified, userFernanda.isVerified)
        }
    }
}