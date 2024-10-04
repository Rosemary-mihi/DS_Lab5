import java.util.*;

//import MyStack.Node;
public class History
{

	public Stack<Event> undoStack = new Stack<Event>();
	public Stack<Event> redoStack = new Stack<Event>();

    /**
       Notepad will call this function when thier text changes.

       deletion is a boolean that indicates if the action was a deletion of text or the insertion of text.
       position is the postion where the change took place
       Change is the string of characters that is the change.
     */
   public void addEvent(boolean deletion, int position, String Change)
   {
	   Event E = new Event(deletion,position,Change);
	   undoStack.push(E);
	   redoStack.clear();
   }

	/**
       Notepad will call this function when it wishes to undo the last event.

       note is a variable to the Notepad that called this function
     */
   public void undoEvent(NotePad note)
   {
	   if (this.hasUndoData() == true)
	   {
		   Event T = undoStack.pop();
		   redoStack.push(T);
		   if (T.deletion == true)
		   {
			   note.insert(T.position, T.Change);
		   }
		   else
		   {
			   note.remove(T.position, T.Change.length());
		   }
	   }
   }


    /**
       Notepad will call this function when it wishes to redo the last event that was undone.
       Note that new actions should clear out events that can be "redone"
       note is a variable to the Notepad that called this function
     */
   public void redoEvent(NotePad note)
   {
	   if (this.hasReDoData() == true)
	   {
		   Event T = redoStack.pop();
		   undoStack.push(T);
		   if (T.deletion == true)
		   {
			   note.remove(T.position, T.Change.length());
		   }
		   else
		   {
			   note.insert(T.position, T.Change);
		   }
	   }
   }

    /**
       returns true if there is undo data in the History
     */
   public boolean hasUndoData()
   {
       if (undoStack.isEmpty() == true)
       {
    	   return false;
       }
	   return true;
   }

    /**
       returns true if there is undo data in the History
     */
   public boolean hasReDoData()
   {
	   if (redoStack.isEmpty() == true)
       {
    	   return false;
       }
	   return true;
   }
	

}
