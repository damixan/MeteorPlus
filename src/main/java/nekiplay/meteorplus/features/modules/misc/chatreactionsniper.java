package 

import meteordevelopment.meteorclient.events.game.ReceiveMessageEvent;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.systems.modules.Categories;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.text.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatReactionSniper extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    // Patterns for different game types
    private final Pattern FILL_WORD_PATTERN = Pattern.compile("fill in the word: ([_]+)alth");
    private final Pattern MATH_PATTERN = Pattern.compile("(\\d+) ([+\\-*]) (\\d+)");
    private final Pattern WRITE_WORD_PATTERN = Pattern.compile("`&b(.+?)&b`");

    public ChatReactionSniper() {
        super(Categories.Misc, "chat-reaction-sniper", "Automatically responds to chat games.");
    }

    @EventHandler
    private void onMessageReceive(ReceiveMessageEvent event) {
        String message = event.getMessage().getString().toLowerCase();
        
        if (!message.contains("ᴄʜᴀᴛ ɢᴀᴍᴇs")) return;

        // Handle fill word game
        Matcher fillWordMatcher = FILL_WORD_PATTERN.matcher(message);
        if (fillWordMatcher.find()) {
            mc.player.sendMessage(Text.literal("wealth"), false);
            return;
        }

        // Handle math game
        Matcher mathMatcher = MATH_PATTERN.matcher(message);
        if (mathMatcher.find()) {
            int num1 = Integer.parseInt(mathMatcher.group(1));
            String operator = mathMatcher.group(2);
            int num2 = Integer.parseInt(mathMatcher.group(3));
            int result;

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                default:
                    return;
            }

            mc.player.sendMessage(Text.literal(String.valueOf(result)), false);
            return;
        }

        // Handle write word game
        Matcher writeWordMatcher = WRITE_WORD_PATTERN.matcher(message);
        if (writeWordMatcher.find()) {
            String word = writeWordMatcher.group(1);
            mc.player.sendMessage(Text.literal(word), false);
        }
    }
}
