package Project;

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

      BarcodeImage bc = new BarcodeImage(imageIn_0);
      DataMatrix dm = new DataMatrix(bc);

      // First secret message
      dm.translateImageToText();
      dm.displayTextToConsole();
      dm.displayImageToConsole();

      // second secret message
      bc = new BarcodeImage(imageIn_1);
      dm.scan(bc);
      dm.translateImageToText();
      dm.displayTextToConsole();
      dm.displayImageToConsole();

      // create your own message
      dm.readText("Believe in yourself.");
      dm.displayImageToConsole();
      
      dm.generateImageFromText();
      dm.displayTextToConsole();
      dm.displayImageToConsole();

      // BarcodeImage bc4 = new BarcodeImage();
      DataMatrix dm4 = new DataMatrix("A string becomes a box.");
      dm4.generateImageFromText();
      dm4.displayTextToConsole();
      dm4.displayImageToConsole();
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

      for(int i = 0; i < MAX_HEIGHT; i++)
      {
         for(int j = 0; j < MAX_WIDTH; j++)
         {
            imageData[i][j] = false;
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
      if(imageData == null)
      {
         return false;
      }

      if(row < 0 | row >= MAX_HEIGHT)
      {
         return false;
      }

      if(col < 0 | col >= MAX_WIDTH)
      {
         return false;
      }

      return imageData[row][col];
   }

   // Purpose: Sets the pixel value to true or false.
   // This function returns true if successful or false otherwise.
   public boolean setPixel(int row, int col, boolean value)
   {
      if(imageData == null)
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

      if(columns < 0 | columns >= MAX_WIDTH)
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
   // Static Members
   public static final char BLACK_CHAR = '*';
   public static final char WHITE_CHAR = ' ';

   // Members
   private BarcodeImage image;
   private String text;
   private int actualWidth;
   private int actualHeight;

   // Default constructor
   DataMatrix()
   {
      this.image = new BarcodeImage();
      this.text = "";
      this.actualWidth = 0;
      this.actualHeight = 0;
   }

   DataMatrix(String text)
   {
      this.image = new BarcodeImage();
      readText(text);
      this.actualWidth = 0;
      this.actualHeight = 0;
   }

   DataMatrix(BarcodeImage image)
   {
      scan(image);
      this.text = "";
   }

   public int getActualHeight()
   {
      return this.actualHeight;
   }

   public int getActualWidth()
   {
      return this.actualWidth;
   }

   // Purpose: Computes the width of the signal by counting 
   // the bottom.
   private int computeSignalWidth()
   {
      int imageWidth = 0;
      for(int col = 0; col < BarcodeImage.MAX_WIDTH; col++)
      {
         if(image.getPixel(BarcodeImage.MAX_HEIGHT - 1, col))
            imageWidth++;
      }
      return imageWidth;
   }

   // Purpose: computes the height of the signal by counting the 
   // left.
   private int computeSignalHeight()
   {
      int imageHeight = 0;
      for(int row = 0; row < BarcodeImage.MAX_HEIGHT; row++)
      {
         if(image.getPixel(row, 0))
            imageHeight++;
      }
      return imageHeight;
   }

   // Purpose: This is the image's mutator.
   public boolean scan(BarcodeImage image)
   {
      if(image == null) // validation
         return false;

      this.image = (BarcodeImage)image.clone();
      
      if(this.image == null) // validation
         return false;

      cleanImage();
      this.actualWidth = computeSignalWidth();
      this.actualHeight = computeSignalHeight();

      return true;
   }

   // Purpose: Set our internal text variable if
   // we receive valid text.
   public boolean readText(String text)
   {
      if(text == null | text.length() > BarcodeImage.MAX_WIDTH-2)
         return false;

      this.text = text;
      return true;
   }

   public boolean generateImageFromText()
   {
      actualWidth = this.text.length() + 2;
      actualHeight = 10;

      clearImage();

      // add borders

      // top and bottom.
      for(int i = 0; i < actualWidth; i++)
      {
         int row = BarcodeImage.MAX_HEIGHT - 1;

         this.image.setPixel(row, i, true);

         // for even spaces
         if(i % 2 == 0)
         {
            row = BarcodeImage.MAX_HEIGHT - this.actualHeight;
            this.image.setPixel(row, i, true);
         }
      }

      // left and right.
      for(int i = 0; i < 10; i++)
      {
         int row = BarcodeImage.MAX_HEIGHT - this.actualHeight + i;
         int col = this.actualWidth - 1;

         this.image.setPixel(row, 0, true);

         if(i % 2 == 0)
         {
            this.image.setPixel(row, col, true);
         }
      }

      // get string ascii.
      for(int arrIndex = 0; arrIndex < text.length(); arrIndex++)
      {
         writeCharToCol(arrIndex, (int) text.charAt(arrIndex));
      }

      return true; // this function never returns false...
   }

   // Purpose: Write characters to the column of an array.
   private boolean writeCharToCol(int col, int asciiCode)
   {
      int ascii_1 = 49;

      String data = Integer.toString(asciiCode, 2);

      for(int index = 0; index < data.length(); index++)
      {
         if( (int)(data.charAt(index)) == ascii_1)
         {
            int row = BarcodeImage.MAX_HEIGHT - this.actualHeight - 1 +
                      (this.actualHeight - data.length()) + index;
            
            this.image.setPixel(row , col + 1, true);
         }
         
      }
      return true;
   }

   // Purpose: well... it reads a char from a col.
   // give it a column and it will read it.
   private char readCharFromCol(int col)
   {
      int count = 0;
      int row = BarcodeImage.MAX_HEIGHT - 2;
      int rowUpperLimit = BarcodeImage.MAX_HEIGHT - getActualHeight() + 2;

      for (; row >= rowUpperLimit; row--)
      {
         if(this.image.getPixel(row, col))
            count += (Math.pow(2, ((BarcodeImage.MAX_HEIGHT - 2) - row)));
      }

      return (char) (count);
   }

   // Purpose: The name says it all. We return a string.
   // The string is a representation of the image.
   public boolean translateImageToText()
   {
      String imageText = " ";
      int col = 1;
      int upperLimit = getActualWidth() - 1;

      for(; col < upperLimit; col++)
      {
         imageText += readCharFromCol(col);
      }

      this.text = imageText;
      return true;
   }

   // Calls system.out.println with our text.
   public void displayTextToConsole()
   {
      System.out.println(this.text);
   }

   public void displayImageToConsole()
   {
      String dash = "-";

      int startPos = 0;
      int endPos = getActualWidth() + 2;

      // Repeat the dash to a certain width
      for(int i = startPos; i < endPos; i++)
         dash += "-";

      // top border.
      System.out.println(dash);

      int rowStart = (BarcodeImage.MAX_HEIGHT - getActualHeight());
      int rowEnd = BarcodeImage.MAX_HEIGHT;

      for(int row = rowStart; row < rowEnd; row++)
      {
         System.out.print("|");
         
         int colStart = 0;
         int colEnd = getActualWidth();

         for(int col = colStart; col < colEnd; col++)
         {
            if(image.getPixel(row, col) == true)
            {
               System.out.print(BLACK_CHAR);
            }
            else
            {
               System.out.print(WHITE_CHAR);
            }

            System.out.print("|");
         }
         System.out.println();
      }

      // bottom border
      System.out.println(dash);
   }

   // Purpose: This is a recursive method that will
   // call itself as long as it can't find data on the borders.
   // It will shift the image down or left if a data check fails.
   public void cleanImage()
   {
      boolean checkData = false;

      int colStart = 0;
      int colEnd = BarcodeImage.MAX_WIDTH;
      int rowStart = 0;
      int rowEnd = BarcodeImage.MAX_HEIGHT;

      // vertical
      for(int col = colStart; col < colEnd; col++)
      {
         if(image.getPixel(rowEnd-1, col))
            checkData = true;
      }

      if(checkData == false)
      {
         shiftImageDown();
         cleanImage();
      }

      // horizontal
      checkData = false; // reset flag
      for(int row = rowStart; row < rowEnd; row++)
      {
         if(image.getPixel(row, 0))
            checkData = true;
      }

      if(checkData == false)
      {
         shiftImageLeft();
         cleanImage();
      }
   }

   private void shiftImageDown()
   {
      int rowStart = BarcodeImage.MAX_HEIGHT - 1;
      int rowEnd = 0;
      int colStart = 0;
      int colEnd = BarcodeImage.MAX_WIDTH;

      for(int row = rowStart; row > rowEnd; row--)
      {
         for(int col = colStart; col < colEnd; col++)
         {
            boolean value = this.image.getPixel(row-1, col);
            this.image.setPixel(row, col, value);
         }
      }
   }

   private void shiftImageLeft()
   {
      int rowStart = 0;
      int rowEnd = BarcodeImage.MAX_HEIGHT;
      int colStart = 0;
      int colEnd = BarcodeImage.MAX_WIDTH - 1;

      for(int row = rowStart; row < rowEnd; row++)
      {
         for(int col = colStart; col < colEnd; col++)
         {
            boolean value = this.image.getPixel(row, col+1);
            this.image.setPixel(row, col, value);
         }
      }
   }

   private void clearImage()
   {
      int rowStart = 0;
      int rowEnd = BarcodeImage.MAX_HEIGHT - 1;
      int colStart = 0;
      int colEnd = BarcodeImage.MAX_WIDTH - 1;

      for(int row = rowStart; row < rowEnd; row++)
         for(int col = colStart; col < colEnd; col++)
            this.image.setPixel(row, col, false);
   }
}

