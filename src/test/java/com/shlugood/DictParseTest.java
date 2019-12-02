package com.shlugood;

import org.junit.Test;

import java.net.URISyntaxException;
import java.util.List;
import com.google.gson.JsonObject;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * some method for dict parse test
 *
 * @author Liping
 * @version 1.0.0
 * @date 2019/12/2
 */
public class DictParseTest {

    private String sentence1 = "馨港庄v溪头村";
    private String dictName = "coolGuy";

    @Test
    public void parse() throws URISyntaxException {
        System.out.println("begin dict parse test.");
        WordCuter wordCuter = WordCuter.createInstance("dic.txt");

        List<List<HitWord>> hitWordResult = wordCuter.createDag(sentence1);

        JsonObject jFinalObj = new JsonObject();
        JsonObject jUpdateObj = new JsonObject();
        int index = 0;
        for (List<HitWord> hitList : hitWordResult) {
            for (HitWord hw : hitList) {
                String val = sentence1.substring(hw.getBeginIndex(), hw.getEndIndex() + 1);
                String key = dictName;
                if (index > 0) {
                    key = key.concat("[").concat(String.valueOf(index)).concat("]");
                }
                jUpdateObj.addProperty(key, val);
                index++;
            }
        }

        if (index > 0) {
            JsonObject jMsgObj = new JsonObject();
            jMsgObj.add("update", jUpdateObj);
            jFinalObj.add("msg_response", jMsgObj);
            jFinalObj.addProperty("status_code", 0);
        } else {
            jFinalObj.addProperty("status_code", -1);
        }

        System.out.println("Final result : " + jFinalObj.toString());

        assertNotNull("parse failed", jFinalObj);
    }
}
