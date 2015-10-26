
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hanxi
 */
public class ArtworkModel {
    private String pictureTag; // The search string of the desired picture
    private String pictureURL; // The URL of the picture image

    /**
     * Arguments.
     *
     * @param searchTag The tag of the photo to be searched for.
     */
    public void doArtworkSearch(String searchTag) {
        System.out.println("search "+searchTag);
        pictureTag = searchTag;
        String response = "";

        // Create a URL for the desired page
        //http://digital.library.illinoisstate.edu/cdm/search/collection/icca/searchterm/apple
        String flickrURL = "http://digital.library.illinoisstate.edu/cdm/search/collection/icca/searchterm/" + searchTag + "*";
        response = fetch(flickrURL);

        //item_id="2527"
        //http://digital.library.illinoisstate.edu/utils/ajaxhelper/?CISOROOT=icca&CISOPTR=2046&&action=2&DMSCALE=60&DMWIDTH=512&DMHEIGHT=479
        int cutLeft = response.indexOf("item_id=\"");
        // If not found, then no such photo is available.
        if (cutLeft == -1) {
            pictureURL = pictureTag = null;
            return;
        }
        // I don't want the src=" part, so skip 5 characters
        cutLeft += 9;

        // Look for the close quote 
        int cutRight = response.indexOf("\"", cutLeft);

        // Now snip out the part from positions cutLeft to cutRight
        pictureURL = "http://digital.library.illinoisstate.edu/utils/ajaxhelper/?CISOROOT=icca&CISOPTR="
                +response.substring(cutLeft, cutRight);
        //System.out.println("pictureURL= " + pictureURL);
    }

    /*
     * Return a URL of an image of appropriate size
     * 
     * Arguments
     * @param picsize The string "mobile" or "desktop" indicating the size of
     * photo requested.
     * @return The URL an image of appropriate size.
     */
    //http://digital.library.illinoisstate.edu/utils/ajaxhelper/?CISOROOT=icca&CISOPTR=2046&&action=2&DMSCALE=60&DMWIDTH=512&DMHEIGHT=479
    public String artworkSize(String picsize) {
        
        String size ="";
        if(picsize.equals("desktop")){
            size = pictureURL+"&&action=2&DMSCALE=60&DMWIDTH=559&DMHEIGHT=992";
        }
        else{
            size = pictureURL+"&&action=2&DMSCALE=30&DMWIDTH=271&DMHEIGHT=480";
        }
        return size;
    }

    /*
     * Return the picture tag.  I.e. the search string.
     * 
     * @return The tag that was used to search for the current picture.
     */
    public String getPictureTag() {
        return pictureTag;
    }
    
    public String getPictureURL(){
        return pictureURL;
    }

    /*
     * Make an HTTP request to a given URL
     * 
     * @param urlString The URL of the request
     * @return A string of the response from the HTTP GET.  This is identical
     * to what would be returned from using curl on the command line.
     */
    private String fetch(String urlString) {
        //System.out.println("get response");
        String response = "";
        try {
            URL url = new URL(urlString);
            
            /*
             * Create an HttpURLConnection.  This is useful for setting headers
             * and for getting the path of the resource that is returned (which 
             * may be different than the URL above if redirected).
             * HttpsURLConnection (with an "s") can be used if required by the site.
             */
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Read all the text returned by the server
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String str;
            // Read each line of "in" until done, adding each to "response"
            while ((str = in.readLine()) != null) {
                // str is one line of text readLine() strips newline characters
                response += str;
                //System.out.println(in);
            }
            in.close();
        } catch (IOException e) {
            System.out.println("Eeek, an exception");
            // Do something reasonable.  This is left for students to do.
        }
        //System.out.println("response: "+response);
        return response;
    }
}
