class BarcodeImage implements Cloneable 
{
    // Data
    public static final int MAX_HEIGHT = 30;
    public static final int MAX_WIDTH = 65;
    private boolean [][] imageData;
    
    // Method
    BarcodeImage()
    {
        imageData = new boolean [MAX_HEIGHT * MAX_WIDTH];

        for (int row = 0; row < imageData.length; row++)
        {
            for (int column = 0; column < imageData[row].length; column++)
            {
                imageData[row][column] = false;
            }
        }
    }

    BarcodeImage(String[] strData)
    {
        imageData = new boolean [MAX_HEIGHT * MAX_WIDTH];

        /*
        This constructor is a little tricky 
        because the incoming image is not the necessarily same size as the internal matrix.  
        So, you have to pack it into the lower-left corner of the double array, 
        causing a bit of stress if you don't like 2D counting.  This is good 2D array exercise.  
        The DataMatrix class will make sure that there is no extra space below or left of the image 
        so this constructor can put it into the lower-left corner of the array.
        */

    }

    public boolean final getPixel(int row, int col)
    {
        if (row == null | col == null)
        {
            return false;
        }

        return true;
    }

    public boolean setPixel(int row, int col, boolean value)
    {

    }

    private void checkSize(String[] data)
    {

    }

    public clone()
    {
        try
        {
            BarcodeImage copy = (BarcodeImage) super.clone();
            copy.imageData = imageData.clone();
            
            for(int i = 0; i < MAX_HEIGHT; i++)
            {
                copy.imageData[i] = imageData[i];
            }
            
            return copy;
        }
        catch(CloneNotSupportedException e)
        {
            return null;
        }
     }
}