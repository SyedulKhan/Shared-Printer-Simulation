package SharedPrinter;

/**
 *
 * @author Syedu
 */
public class Document {
    
    private final String userID;
    private final String documentName;
    private final int numOfPages;
    
    public Document (String userID, String documentName, int numOfPages){
        this.userID = userID;
        this.documentName = documentName;
        this.numOfPages = numOfPages;
    }

    public String getUserID() {
        return userID;
    }

    public String getDocumentName() {
        return documentName;
    }

    public int getNumOfPages() {
        return numOfPages;
    }
    
    @Override
    public String toString(){
        return "Document[ "  +
                "UserID: " + userID        + ", " +
                "Name: "   + documentName  + ", " +
                "Pages: "  + numOfPages +
                " ]" ;
    }
    
    
    
}
