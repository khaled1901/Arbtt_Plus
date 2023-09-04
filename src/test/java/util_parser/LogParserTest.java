package util_parser;

import cmd.LogParser;
import model.TimeLogEntry;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

public class LogParserTest {

   @Test
  void parseJsonString(){
    String json = """
        [
        {"date":"2023-03-07T11:22:00.6710116Z","windows":[{"program":"Program Manager","active":false,"title":"\\\\Device\\\\HarddiskVolume3\\\\Windows\\\\explorer.exe"},{"program":"Windows-Eingabeerfahrung","active":false,"title":"\\\\Device\\\\HarddiskVolume3\\\\Windows\\\\SystemApps\\\\MicrosoftWindows.Client.CBS_cw5n1h2txyewy\\\\TextInputHost.exe"},{"program":"Arbtt_Projekt ÔÇô JsonList.java Administrator","active":false,"title":"\\\\Device\\\\HarddiskVolume3\\\\Program Files\\\\JetBrains\\\\IntelliJ IDEA Community Edition 2022.2.1\\\\bin\\\\idea64.exe"},{"program":"Momente ÔÇô Application.java [arbtt] Administrator","active":false,"title":"\\\\Device\\\\HarddiskVolume3\\\\Program Files\\\\JetBrains\\\\IntelliJ IDEA Community Edition 2022.2.1\\\\bin\\\\idea64.exe"},{"program":"#binarium | BINARIUM - Discord","active":false,"title":"\\\\Device\\\\HarddiskVolume3\\\\Users\\\\CC-Student\\\\AppData\\\\Local\\\\Discord\\\\app-1.0.9011\\\\Discord.exe"},{"program":"Out-File (Microsoft.PowerShell.Utility) - PowerShell | Microsoft Learn - Google Chrome","active":false,"title":"\\\\Device\\\\HarddiskVolume3\\\\Program Files\\\\Google\\\\Chrome\\\\Application\\\\chrome.exe"},{"program":"Administrator: Windows PowerShell","active":true,"title":"\\\\Device\\\\HarddiskVolume3\\\\Windows\\\\System32\\\\WindowsPowerShell\\\\v1.0\\\\powershell.exe"}],"rate":60000,"desktop":"","inactive":2610},
        {"date":"2023-03-07T11:23:00.6746834Z","windows":[{"program":"Program Manager","active":false,"title":"\\\\Device\\\\HarddiskVolume3\\\\Windows\\\\explorer.exe"},{"program":"Windows-Eingabeerfahrung","active":false,"title":"\\\\Device\\\\HarddiskVolume3\\\\Windows\\\\SystemApps\\\\MicrosoftWindows.Client.CBS_cw5n1h2txyewy\\\\TextInputHost.exe"},{"program":"Arbtt_Projekt ÔÇô JsonList.java Administrator","active":false,"title":"\\\\Device\\\\HarddiskVolume3\\\\Program Files\\\\JetBrains\\\\IntelliJ IDEA Community Edition 2022.2.1\\\\bin\\\\idea64.exe"},{"program":"Momente ÔÇô Application.java [arbtt] Administrator","active":false,"title":"\\\\Device\\\\HarddiskVolume3\\\\Program Files\\\\JetBrains\\\\IntelliJ IDEA Community Edition 2022.2.1\\\\bin\\\\idea64.exe"},{"program":"#binarium | BINARIUM - Discord","active":false,"title":"\\\\Device\\\\HarddiskVolume3\\\\Users\\\\CC-Student\\\\AppData\\\\Local\\\\Discord\\\\app-1.0.9011\\\\Discord.exe"},{"program":"Out-File (Microsoft.PowerShell.Utility) - PowerShell | Microsoft Learn - Google Chrome","active":false,"title":"\\\\Device\\\\HarddiskVolume3\\\\Program Files\\\\Google\\\\Chrome\\\\Application\\\\chrome.exe"},{"program":"Administrator: Windows PowerShell","active":true,"title":"\\\\Device\\\\HarddiskVolume3\\\\Windows\\\\System32\\\\WindowsPowerShell\\\\v1.0\\\\powershell.exe"}],"rate":60000,"desktop":"","inactive":47},
        {"date":"2023-03-07T11:24:00.6766778Z","windows":[{"program":"Program Manager","active":false,"title":"\\\\Device\\\\HarddiskVolume3\\\\Windows\\\\explorer.exe"},{"program":"Windows-Eingabeerfahrung","active":false,"title":"\\\\Device\\\\HarddiskVolume3\\\\Windows\\\\SystemApps\\\\MicrosoftWindows.Client.CBS_cw5n1h2txyewy\\\\TextInputHost.exe"},{"program":"Arbtt_Projekt ÔÇô JsonList.java Administrator","active":false,"title":"\\\\Device\\\\HarddiskVolume3\\\\Program Files\\\\JetBrains\\\\IntelliJ IDEA Community Edition 2022.2.1\\\\bin\\\\idea64.exe"},{"program":"Momente ÔÇô Application.java [arbtt] Administrator","active":false,"title":"\\\\Device\\\\HarddiskVolume3\\\\Program Files\\\\JetBrains\\\\IntelliJ IDEA Community Edition 2022.2.1\\\\bin\\\\idea64.exe"},{"program":"#binarium | BINARIUM - Discord","active":false,"title":"\\\\Device\\\\HarddiskVolume3\\\\Users\\\\CC-Student\\\\AppData\\\\Local\\\\Discord\\\\app-1.0.9011\\\\Discord.exe"},{"program":"Out-File (Microsoft.PowerShell.Utility) - PowerShell | Microsoft Learn - Google Chrome","active":false,"title":"\\\\Device\\\\HarddiskVolume3\\\\Program Files\\\\Google\\\\Chrome\\\\Application\\\\chrome.exe"},{"program":"Administrator: Windows PowerShell","active":true,"title":"\\\\Device\\\\HarddiskVolume3\\\\Windows\\\\System32\\\\WindowsPowerShell\\\\v1.0\\\\powershell.exe"}],"rate":60000,"desktop":"","inactive":890}
        ]
        """;
    var parser = new LogParser( new ByteArrayInputStream( json.getBytes( StandardCharsets.UTF_8 ) ) );
    for ( TimeLogEntry timeLogEntry : parser ) {
      System.out.println( timeLogEntry );
    }
  }
}
