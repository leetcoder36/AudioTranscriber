# AudioTranscriber

This program transcribes audio into text and writes the transcription into a local text file on the user's device.
It does this by making HTTP requests to the AssemblyAI RESTful API which offers speech to text transcription services.
The audio file needs to be accessible by a link (as an example, I uploaded an audio file into this repository and linked it within the program
The program uses the GSON library which allows Java objects to be converted into JSON files, which are needed to communicate with the API
