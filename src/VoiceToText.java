import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class VoiceToText {

    public VoiceToText() {
    }

    public void transcribe()
    {
        try {
            Transcript transcript = new Transcript();
            transcript.setAudio_url("https://github.com/leetcoder36/AudioTranscriber/blob/main/American%20Psycho%20-%20Business%20Card%20scene%20%5BHD%20-%20720p%5D.mp4?raw=true");
            Gson gson = new Gson();
            String jsonRequest = gson.toJson(transcript);

            HttpRequest postRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://api.assemblyai.com/v2/transcript"))
                    .header("Authorization", "4f4846275e8148e0906cc8bd996ddd2b")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();

            HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());

            System.out.println(postResponse.body());

            transcript = gson.fromJson(postResponse.body(), Transcript.class);

            System.out.println(transcript.getId());

            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://api.assemblyai.com/v2/transcript/" + transcript.getId()))
                    .header("Authorization", "4f4846275e8148e0906cc8bd996ddd2b")
                    .build();
            while (true) {
                HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
                transcript = gson.fromJson(getResponse.body(), Transcript.class);
                System.out.println(transcript.getStatus());
                if (transcript.getStatus().equals("completed") || transcript.getStatus().equals("error")) {

                    break;
                }
                Thread.sleep(1000);

            }

            FileOutput output = new FileOutput();
            output.write(transcript.getText(), "transcription.txt");


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        

    }
}
