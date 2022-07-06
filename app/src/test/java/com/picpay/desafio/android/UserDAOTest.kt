package com.picpay.desafio.android
//
//import com.picpay.desafio.android.data.datasource.local.AppDatabase
//import com.picpay.desafio.android.data.datasource.local.dao.UserDao
//import com.picpay.desafio.android.data.datasource.local.model.UserEntity
//import org.junit.After
//import org.junit.AfterClass
//import org.junit.BeforeClass
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.junit.runners.JUnit4
//import org.koin.core.context.stopKoin
//import org.koin.test.KoinTest
//import org.koin.test.inject
//import kotlin.test.assertNotNull
//
//@RunWith(JUnit4::class)
//class UserDAOTest : KoinTest {
//
//    /*
//     * Inject needed components from Koin
//     */
//    private val appDatabase: AppDatabase by inject()
//    private val userDao: UserDao by inject()
//
//    companion object {
//        @BeforeClass
//        @JvmStatic
//        fun setup() {
//            configureTestAppComponent()
//        }
//
//        /**
//         * Close resources
//         */
//        @AfterClass
//        @JvmStatic
//        fun tearDown() {
//            stopKoin()
//        }
//    }
//
//    @Test
//    fun `save user in database`() {
//        // Save entities
//        val entities = createUsers().apply {
//            map { userDao.addUser(it) }
//        }
//
//        // Keep id for each one
//        val ids = entities.map { it.id }
//
//        assertNotNull(entities)
//    }
//
//    private fun createUsers(): List<UserEntity> {
//        return listOf(
//            UserEntity(1, "img", "User 1", "user1"),
//            UserEntity(2, "img", "User 2", "user2"),
//            UserEntity(3, "img", "User 3", "user3"),
//            UserEntity(4, "img", "User 4", "user4"),
//            UserEntity(5, "img", "User 5", "user5"),
//            UserEntity(6, "img", "User 6", "user6"),
//            UserEntity(7, "img", "User 7", "user7"),
//            UserEntity(8, "img", "User 8", "user8"),
//            UserEntity(9, "img", "User 9", "user9"),
//            UserEntity(10, "img", "User 10", "user10")
//        )
//    }
//
//    @After
//    fun after() {
//        appDatabase.close()
//    }
//}