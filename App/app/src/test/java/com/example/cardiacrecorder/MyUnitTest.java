package com.example.cardiacrecorder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class MyUnitTest {

    @Test
    public void addDataTest(){
        RecordList recordList= new RecordList();
        DataModel dataModel= new DataModel("1-1-2020","1:20","66", "166", "77", "good");
        recordList.addRecord(dataModel);
        assertEquals(1,recordList.myArraylist.size());

        RecordList recordList1= new RecordList();
        DataModel dataModel1= new DataModel("1-1-2020","1:20","66", "166", "77", "good");
        recordList.addRecord(dataModel1);
        assertEquals(2,recordList.myArraylist.size());
        assertTrue(recordList.myArraylist.contains(dataModel));
        assertTrue(recordList.myArraylist.contains(dataModel1));

        recordList.deleteRecord(0);
        recordList.deleteRecord(0);

    }


    @Test
    public void  addRecordExTest(){
        RecordList recordList= new RecordList();
        DataModel dataModel= new DataModel("1-1-2020","1:20","66", "166", "77", "good");
        recordList.addRecord(dataModel);
        assertThrows(IllegalArgumentException.class, () -> recordList.addRecord(dataModel));
        recordList.deleteRecord(0);
    }

    @Test
    public void testDeleteRecord(){
        RecordList recordList= new RecordList();
        DataModel record1= new DataModel("1-1-2020","1:20","66", "166", "77", "good");
        recordList.addRecord(record1);
        assertEquals(1,recordList.myArraylist.size());

        RecordList recordList1= new RecordList();
        DataModel record2= new DataModel("1-1-2020","1:20","66", "166", "77", "good");
        recordList.addRecord(record2);
        assertEquals(2, recordList.myArraylist.size());

        assertTrue(recordList.myArraylist.contains(record1));
        assertTrue(recordList.myArraylist.contains(record2));

        recordList.deleteRecord(0);
        assertEquals(1, recordList.myArraylist.size());
        assertFalse(recordList.myArraylist.contains(record1));
        assertThrows(IllegalArgumentException.class, () -> recordList.deleteRecord(1));
        recordList.deleteRecord(0);
    }

    @Test
    public void testCount(){
        RecordList recordList= new RecordList();
        DataModel record= new DataModel("1-1-2020","1:20","66", "166", "77", "good");
        recordList.addRecord(record);

        assertEquals(1,recordList.count());

        recordList.deleteRecord(0);
    }



}
