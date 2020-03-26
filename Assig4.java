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

// Phase 1: BarcodeImage

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

