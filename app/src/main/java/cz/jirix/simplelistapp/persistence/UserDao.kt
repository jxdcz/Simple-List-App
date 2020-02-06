package cz.jirix.simplelistapp.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import cz.jirix.simplelistapp.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<User>)

    @Delete
    fun delete(user: User)

}
