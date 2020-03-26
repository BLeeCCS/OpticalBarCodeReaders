// ////////////////////////////////////
// Assignment 4: Optical Barcode 
//               Readers and Writers

// [CST 338] Software Design
// Professor Cecil

// Authors: Timothy (Andrew) Hinea  -.-
//          Brandon Lee             o.0
//          Justin Dinkenbach       :)
//          David Sullivan          >:D

// Emails: thinea@csumb.edu
//         lee7114@csumb.edu
//         jdinkelbach@csumb.edu
//         dsullivan@csumb.edu

// ////////////////////////////////////

public class Assig4
{
   public static void main(String[] args) 
   {
      String[] imageIn_0 =
              {
                      "                                               ",
                      "                                               ",
                      "                                               ",
                      "     * * * * * * * * * * * * * * * * * * * * * ",
                      "     *                                       * ",
                      "     ****** **** ****** ******* ** *** *****   ",
                      "     *     *    ****************************** ",
                      "     * **    * *        **  *    * * *   *     ",
                      "     *   *    *  *****    *   * *   *  **  *** ",
                      "     *  **     * *** **   **  *    **  ***  *  ",
                      "     ***  * **   **  *   ****    *  *  ** * ** ",
                      "     *****  ***  *  * *   ** ** **  *   * *    ",
                      "     ***************************************** ",
                      "                                               ",
                      "                                               ",
                      "                                               "
              };

      String[] imageIn_1 =
              {
                      "                                          ",
                      "                                          ",
                      "* * * * * * * * * * * * * * * * * * *     ",
                      "*                                    *    ",
                      "**** *** **   ***** ****   *********      ",
                      "* ************ ************ **********    ",
                      "** *      *    *  * * *         * *       ",
                      "***   *  *           * **    *      **    ",
                      "* ** * *  *   * * * **  *   ***   ***     ",
                      "* *           **    *****  *   **   **    ",
                      "****  *  * *  * **  ** *   ** *  * *      ",
                      "**************************************    ",
                      "                                          ",
                      "                                          ",
                      "                                          ",
                      "                                          "
              };
   }
}

// Phase 1: BarcodeIO Interface.

// Any class that implements BarcodeIO is expected to store
// some version of an image and some version of the text 
// associated with that image.

interface BarcodeIO
{
   public boolean scan(BarcodeImage bc);
   public boolean readText(String text);
   public boolean generateImageFromText();
   public boolean translateImageToText();

   public void displayTextToConsole();
   public void displayImageToConsole();
};

// Phase 1: BarcodeImage Class.

// An object of this BarcodeImage class will be one of the 
// main member-objects of the class that comes next.  BarcodeImage 
// will describe the 2D dot-matrix pattern, or "image".  It will 
// contain some methods for storing, modifying and retrieving the 
// data in a 2D image.  The interpretation of the data is not part 
// of this class.  Its job is only to manage the optical data.  It 
// will implement Cloneable interface because it contains deep data.

class BarcodeImage implements Cloneable
{
   public static final int MAX_HEIGHT = 30;
   public static final int MAX_WIDTH = 65;

   private boolean[][] imageData;

   // Purpose: The default constructor instantiates a 2D array
   // of booleans. Dimensions of the array are [MAX_WIDTH][MAXHEIGHT] 
   // and sets all values to false.
   public BarcodeImage()
   {
      imageData = new boolean[MAX_HEIGHT][MAX_WIDTH];

      for(boolean arr[] : imageData)
      {
         for(boolean val : arr)
         {
            val = false;
         }
      }
   }

   // Purpose: Accepts a 1D array of Strings and converts
   // it to our 2D array of booleans.

   public BarcodeImage(String[] data)
   {
      if(validateSize(data))
      {
         imageData = new boolean[MAX_HEIGHT][MAX_WIDTH];
         
         for(int row = 0; row < data.length; row++)
         {
            for(int col = 0; col < data[row].length(); col++)
            {
               if(data[row].charAt(col) == '*')
                  setPixel(row, col, true);
            }
         }
      }
   }

   // Purpose: Gets the boolean value for a pixel at [row][col]
   public boolean getPixel(int row, int col)
   {
      if(imageData != null)
         return false;

      if(row < 0 | row >= MAX_HEIGHT)
         return false;

      if(col < 0 | col >= MAX_WIDTH)
         return false;

      return imageData[row][col];
   }

   // Purpose: Sets the pixel value to true or false.
   // This function returns true if successful or false otherwise.
   public boolean setPixel(int row, int col, boolean value)
   {
      if(imageData != null)
         return false;

      if(row < 0 | row >= MAX_HEIGHT)
         return false;

      if(col < 0 | col >= MAX_WIDTH)
         return false;

      imageData[row][col] = value;

      return true;
   }

   // if the string is too big or too small we return false.
   // if the internal array is null then we also return false.
   private boolean validateSize(String[] data)
   {
      if(data == null)
         return false;

      int rows = data.length;
      int columns = data[0].length();

      if(rows < 0 | rows >= MAX_HEIGHT)
         return false;

      if(rows < 0 | rows >= MAX_WIDTH)
         return false;

      return true;
   }

   // Displays the current barcode image to console.
   public void displayImageToConsole()
   {
      for(int row = 0; row < MAX_HEIGHT; row++)
      {
         for(int col = 0; col < MAX_WIDTH; col++)
         {
            // if the array position is positive we do '*''
            // if the array position is negative we do ' '
            char symbol = imageData[row][col] ? '*' : ' ';

            System.out.print(symbol);
         }
         System.out.println();
      }
   }

   // Purpose: Overrides the method from the Cloneable interface.
   // We return a cloned object.
   @Override
   public Object clone()
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

// Phase 1: DataMatrix

// The class that will contain both a BarcodeImage member object and 
// a text String member that represents the message encoded in the 
// embedded image.  This class has all the fun.  This is not a true 
// Datamatrix because, for one thing, there is no Reed-Solomon error 
// correction. 

class DataMatrix implements BarcodeIO
{
   @Override
   public boolean scan(BarcodeImage bc) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean readText(String text) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean generateImageFromText() {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean translateImageToText() {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public void displayTextToConsole() {
      // TODO Auto-generated method stub

   }

   @Override
   public void displayImageToConsole() {
      // TODO Auto-generated method stub

   }  
}

