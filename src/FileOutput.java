import java.io.BufferedWriter;
import java.io.FileWriter;

public class FileOutput {





    public FileOutput() {


    }

    public void write(String text, String outputFileName, String outputFileDirectory)
    {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileDirectory + outputFileName));
            for(int i = 0; i < text.length(); i++)
            {
                writer.write(text.charAt(i));
                if(text.charAt(i) == '.')
                {
                    writer.write("\n");
                }

            }

            writer.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
