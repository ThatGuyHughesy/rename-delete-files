import java.io.*;
import java.util.*;
import java.lang.*;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.*;

class RenameDeleteFiles 
{
	public static void main(String[] args) 
	{
		Directory d = new Directory(); TheFile f = new TheFile(); Rename r = new Rename(); Delete s = new Delete();
		
		d.putDirectory();
		f.getFile();
		
		Object[] options = {"Rename","Delete"};
		JFrame option = new JFrame("File Options");
		int choice = JOptionPane.showOptionDialog(option, "What Would You Like To Do With This File?", "File Options",
					 JOptionPane.YES_NO_OPTION,
					 JOptionPane.PLAIN_MESSAGE,
					 null, options, options[0]);
		
		if(choice == 0)
		{
			r.getRename(f);
		}
		
		else
		{
			s.getDelete(f);
		}
	}
}

class Directory
{
	void putDirectory()
	{
		JFrame directory = new JFrame("Directory");
		
		String dirName = System.getProperty("user.dir");
		File theDirectory = new File(dirName);
		File[] files = theDirectory.listFiles();
		
		if (files==null)
		{
			JOptionPane.showMessageDialog(directory, dirName+" is not a directory ", "Directory", JOptionPane.PLAIN_MESSAGE);
		}
		
		else 
		{
			int i = 0;
			String [] array = new String [new File(dirName).listFiles().length];
			
			for (File file: files)
			{
				if(i == (new File(dirName).listFiles().length)-1)
				{
					array[i] = ("\n" + file.getName() + "\n" );
				}
				
				else
				{
					array[i] = ("\n" + file.getName());
				}
				
				i++;
			}
			javax.swing.JOptionPane.showMessageDialog(directory, Arrays.toString(array), "Directory", JOptionPane.PLAIN_MESSAGE);
		}
	}
}

class TheFile
{
	String fileName = "";
	void getFile()
	{
		JFrame filename = new JFrame("File Name");
		fileName = (JOptionPane.showInputDialog(filename, "Enter The File Name: ", "File Name", JOptionPane.PLAIN_MESSAGE));
	}
}

class Rename
{		
	void getRename(TheFile f)
	{
		File file = new File(f.fileName);
			
		if(!file.exists())
		{
			JFrame notFound = new JFrame("File Not Found");
			JOptionPane.showMessageDialog(notFound, "Cannot Find File: " + f.fileName, "File Not Found", JOptionPane.PLAIN_MESSAGE); 
			System.exit(0);
		}

		JFrame rename = new JFrame("Rename");
		String newName = JOptionPane.showInputDialog(rename, "Rename File: ", "Rename", JOptionPane.PLAIN_MESSAGE);

		File newFile = new File(newName);
			
		if(file.exists())
		{
			if(newFile.exists())
			{
				JFrame already = new JFrame("The File Already Exists");
				JOptionPane.showMessageDialog(already, "The File Name: " + newName + "Already Exists", "The File Already Exists", JOptionPane.PLAIN_MESSAGE); 
				System.exit(0);
			}
				
			else
			{
				file.renameTo(new File(newName));
				JFrame renamed = new JFrame("File Renamed");
				JOptionPane.showMessageDialog(renamed, "Done!", "File Renamed", JOptionPane.PLAIN_MESSAGE);  
				System.exit(0);
			}
		}	
	}
}

class Delete
{
	void getDelete(TheFile f)
	{
		File file = new File(f.fileName);
			
		if(!file.exists())
		{
			JFrame notFound = new JFrame("File Not Found");
			JOptionPane.showMessageDialog(notFound, "Cannot Find File: " + f.fileName, "File Not Found", JOptionPane.PLAIN_MESSAGE); 
			System.exit(0);
		}

		if(file.exists())
		{
			Object[] deleteOptions = {"Yes","No"};
			JFrame warning = new JFrame("Warning!");
			int delete = JOptionPane.showOptionDialog(warning,
					 "Are You Sure You Want To Delete: " + f.fileName,
					 "Warning!",
					 JOptionPane.YES_NO_OPTION,
					 JOptionPane.PLAIN_MESSAGE,
					 null, deleteOptions, deleteOptions[0]);
					 
			if(delete == 0)
			{
				file.delete();
				JFrame deleted = new JFrame("Deleted");
				JOptionPane.showMessageDialog(deleted, f.fileName + " Was Deleted", "Deleted", JOptionPane.PLAIN_MESSAGE); 
				System.exit(0);
			}
			
			else
			{
				JFrame notDeleted = new JFrame("Not Deleted");
				JOptionPane.showMessageDialog(notDeleted, f.fileName + " Was Not Deleted", "Not Deleted", JOptionPane.PLAIN_MESSAGE); 
				System.exit(0);
			}
		}
	}
}