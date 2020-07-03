package gmbh.conteco;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class Main extends Application implements EventHandler<ActionEvent> {
    Label lblName = new Label("Vollständiger Name");
    private TextField txtName = new TextField();
    private Button btnCalculate = new Button("Ausrechnen");
    private Label lblMeaning = new Label();

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        var nameRow = new HBox(lblName, txtName);
        nameRow.setPadding(new Insets(5,5,5,5));

        var outerBox = new HBox(nameRow, btnCalculate);
        outerBox.setPadding(new Insets(5,5,5,5));

        VBox root = new VBox(outerBox, lblMeaning);

        lblMeaning.setWrapText(true);

        btnCalculate.setOnAction(this);

        // Create the scene using our layout; then display it
        Scene scene = new Scene(root, 420, 270);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setTitle("Mein Name in Zahlen");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent event) {
        // Calculate the numerological value
        int value = numerologicalValue(txtName.getText());

        // Fetch description and display result
        lblMeaning.setText("Die Zahl " + value + " bedeutet: " + describe(value));
    }

    /**
     * Step 1: Convert letter A-Z to numeric value; all other characters receive
     * a value of zero.
     */
    private int letterValue(char letter) {
        int value = 0;
        if (letter >= 'A' && letter <= 'Z') {
            value = (letter - 'A') % 9 + 1;
        }
        return value;
    }

    /**
     * Step 2: Process the letters A-Z. We convert letters a-z to upper case at
     * this point. We return an int[] holding all of the values
     */
    private int[] letterValues(char[] letters) {
        int[] values = new int[letters.length];
        for (int i = 0; i < letters.length; i++) {
            char c = letters[i];
            c = Character.toUpperCase(c);
            values[i] = letterValue(c);
        }
        return values;
    }

    /**
     * Step 3: Sum the digits of an integer
     */
    private int sumOfDigits(int in) {
        int sum = 0;
        while (in > 0) {
            sum += in % 10;
            in = in / 10;
        }
        return sum;
    }

    /**
     * Step 4: Calculate numerological value of a string
     */
    private int numerologicalValue(String name) {
        char[] chars = name.toCharArray();
        int[] values = letterValues(chars);

        // Sum the values
        int sum = 0;
        for (int value : values)
            sum += value;

        // Reduce to one of the final values
        while (sum > 23 || (sum > 11 && sum < 22) || sum == 10) {
            sum = sumOfDigits(sum);
        }

        return sum;
    }

    /**
     * Step 5: Convert values into silly descriptions
     */
    private String describe(int sum) {
        String desc;
        switch (sum) {
            case 1:
                desc = "Menschen mit der Nummer eins wollen in dem was sie tun, die Besten sein. Sie streben nach Erfolg, sind großartige Leader, entschlossen und wollen immer unabhängig sein in ihrem Leben. Deine Bestimmung ist deshalb der Erfolg. Das Geheimnis dahinter liegt jedoch nicht nur im Vorwärtsstreben. Du kannst super erfolgreich werden, in dem du liebst, was du tust. Egal ob du das große Geld machen, du eine gewisse Position in deinem Job erreichen oder ein eigenes Projekt aufziehen willst. Steht dein Herz 100 Prozent dahinter, dann wirst du es schaffen. Gibst du dich hingegen täglich mit Aufgaben ab, die du nicht leiden kannst, wird der natürliche Elan zu Frust verpuffen.";
                break;
            case 2:
                desc = "Die zwei steht in der Numerologie für Charaktere, die sehr gefühlvoll, romantisch und weich sind. Sie stellen sich nicht gerne als Anführer in den Mittelpunkt, sondern folgen lieber Anweisungen der anderen und arbeiten dabei gerne im Team. Was dich erfüllt in diesem Leben ist jedoch kein pralles Konto oder aufregender Job-Titel – du suchst unterbewusst nach dem Zwischenmenschlichen und möchtest dein liebevolles Herz mit anderen Teilen. Du bist laut Numerologie deshalb optimal in sozialen Berufen aufgehoben oder könntest dich nebenher engagieren. Denn das Gefühl, anderen Menschen oder vielleicht Tieren helfen zu können, macht Menschen mit Nummer zwei wirklich glücklich.";
                break;
            case 3:
                desc = "Number drei repräsentiert Menschen, die gesellig, selbstbewusst, laut und abenteuerlustig sind. Sie fühlen sich unter Regeln meist nicht besonders wohl und sollten deshalb besser ihren eigenen Weg gehen. Anstatt jeden Tag mit dem grauen Büroalltag zu kämpfen, ist es für diese Seelen besser, sich kreativ auszuleben und dabei voll im Mittelpunkt stehen. Ein eigener Podcast, eine Karriere auf der Bühne oder eine eigene Agentur gründen – was es auch sein mag, deine Bestimmung ist es, nach deinen eigenen, kreativen Regeln zu spielen und dabei andere zu begeistern.";
                break;
            case 4:
                desc = "Die Nummer vier verkörpert in der Numerologie eine Persönlichkeit, die sehr praktisch veranlagt ist, es liebt, Probleme zu lösen und unter Stress erst richtig gut performt. Ein anspruchsvoller, vielseitiger Job ist optimal für dich. Deine wahre Bestimmung ist es jedoch, eine Familie zu gründen. Du bist ein absoluter Familienmensch und wirst es lieben, das „Management“ zu übernehmen. Denn dein Ziel ist es nicht, den Chef-Sessel zu erobern, sondern etwas zu tun, dass dein Herz erfüllt. Und was gäbe es da Schöneres, als das zufriedene Lächeln von Partner und Nachwuchs, wenn du wieder einmal alle Probleme gelöst oder den Urlaub perfekt organisiert hast?";
                break;
            case 5:
                desc = "Menschen mit der Nummer fünf sind feurige Charaktere, große Visionäre und Abenteurer. Sie hegen kühne Träume und sollten sich diese auch verwirklichen. Heißt konkret: Du bist einfach nicht der Typ, der immer auf Nummer sicher geht und Wochen für Woche von Nine-To-Five auf dem Bürostuhl sitzen möchte. Du folgst lieber deiner Intuition, lässt dich auf spontane Reisen ein oder verwirklichst deine eigenen Ideen. Nur, wenn du dieser inneren Leidenschaft folgst, kannst du dich voll entfalten.";
                break;
            case 6:
                desc = "Die Nummer sechs verkörpert Menschen, die sehr bodenständig, loyal und verantwortungsbewusst sind und es mögen, wenn Dinge geplant oder vorhersehbar sind. Deine Bestimmung ist es deshalb, einen Alltag aufzubauen, der dir Sicherheit gibt und in dem du dich wohlfühlst. Optimal ergänzt durch einen Partner oder Familie, denn in dir steckt ein treues Herz, das sich gerne für andere zurückstellt und die Nähe zu lieben Menschen genießt.";
                break;
            case 7:
                desc = "Die Nummer sieben ist ein absoluter Karriere-Typ. Du bist im Alltag eher Einzelgänger, etwas reserviert und introvertiert. Liebe ist für dich wunderschön, dennoch aber nicht essenziell. Denn du bist mit deinem Job verheiratet, eine steile Karriere ist deine Lebensmission. Du brauchst Erfolgsgefühle, die dir Bestätigung geben und dich immer weiter antreiben. Kein Wunder, dass du jeden Titel ergattern kannst, denn keiner arbeitet so perfektionistisch und schlau wie Menschen mit der Nummer sieben.";
                break;
            case 8:
                desc = "Auch die Nummer acht steht für absolute Workaholics. Dein Ziel liegt jedoch nicht darin, Erfolg zu haben – sondern sich durch den Erfolg einen gewissen Lifestyle leisten können. Die Acht repräsentiert nämlich auch Materialisten, die ihr Leben gerne durch luxuriöse, schöne Dinge definieren, jedoch auch echte Genießer sind. Du liebst es, um die Welt zu reisen, gut zu essen und dich schön zu kleiden und genau das ist deine Bestimmung. Als It-Girl kannst du andere inspirieren und ein eigenes Business daraus zaubern. Vielleicht solltest du irgendwann deinen eigenen Youtube-Kanal starten, Reise-Blogger werden oder ein Restaurant eröffnen?";
                break;
            case 9:
                desc = "Neun ist deine Nummer? Dann suchst du wahrscheinlich schon länger nach dem Sinn des Lebens, denn für dich gibt es nichts Schlimmeres, als Aufgaben ohne tiefere Bedeutung zu erledigen. In einer oberflächlichen Branche, die auf Profit aus sind, bist du deshalb falsch. Du solltest deinen kreativen Geist nutzen und dich in Charity-Projekten engagieren, eine Organisation für einen guten Zweck gründen oder als Umweltpolitiker tätig werden. Was es auch ist, du hast die Gabe, Menschen zu begeistern und kannst mit dieser Anziehungskraft wirklich etwas verändern in dieser Welt. Ob im Großen oder im Kleinen.";
                break;
            default:
                desc = "Ich weis nichts über dich!";
        }
        return desc;
    }
}