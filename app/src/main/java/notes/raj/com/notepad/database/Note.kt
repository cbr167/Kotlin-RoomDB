package notes.raj.com.notepad.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "note_table")
class Note(@field:ColumnInfo
           var title: String?, //@Ignore //if ingore added, these fields will not be added to the table.
           var description: String?//@ColumnInfo(name = "priority_col")  // to have different name to the column
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
} 