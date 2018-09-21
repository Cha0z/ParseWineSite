package com.website.parsers;

import java.util.List;

public class CreatorSectionsParser {

   private List<String> listOfLink;

    public CreatorSectionsParser(List<String> listOfLink) {
        this.listOfLink = listOfLink;
    }

    public void callSectionsParser(){
        VinePageParser vinePageParser = new VinePageParser(listOfLink.get(0));

        vinePageParser.parse();


}

}
