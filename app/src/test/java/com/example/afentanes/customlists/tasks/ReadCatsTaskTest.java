package com.example.afentanes.customlists.tasks;

import android.test.mock.MockApplication;
import android.util.Log;

import com.example.afentanes.customlists.CatInfo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import static java.security.AccessController.getContext;
import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class ReadCatsTaskTest  {

    @Test
    public void getCatsInfoStream() throws Exception {

        List<CatInfo> cats = getReadCatsTaskStub().getCats();
        Assert.assertEquals(6, cats.size());
        Assert.assertEquals("apolo", cats.get(0).id);
        Assert.assertEquals("http://google.com/gatito.jpg", cats.get(2).url);
        Assert.assertEquals("http://google.com/gatito", cats.get(5).source);
    }

    private ReadCatsTask getReadCatsTaskStub(){

        return new ReadCatsTask() {
            @Override
            InputStream getCatsInfoStream() {
                try {
                    return new FileInputStream(this.getClass().getClassLoader().getResource("CatsInfo.xml").getPath() );
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
    }

}