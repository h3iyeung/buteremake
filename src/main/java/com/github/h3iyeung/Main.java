package com.github.h3iyeung;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.component.ActionRow;
import org.javacord.api.entity.message.component.Button;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.interaction.MessageComponentInteraction;
import org.javacord.api.interaction.SlashCommand;
import org.javacord.api.interaction.SlashCommandBuilder;
import org.javacord.api.interaction.SlashCommandInteraction;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Main {

    static String currentRank = "emptybuttonloadingbar.png";

    public static void main(String[] args) {
        DiscordApi api = new DiscordApiBuilder()
                .setToken("MTEzMDkzNDA0NTUwOTk2MzkwNg.GMLogP.TYdoaYSrh9dfcK0Ne2wbeEY4GvNMTyvoQmxS9A")
                .login().join();


        Set<SlashCommandBuilder> builders = new HashSet<>();
        builders.add(SlashCommand.with("bute", "creates the bute"));

        api.bulkOverwriteGlobalApplicationCommands(builders).join();


        api.addMessageComponentCreateListener(event -> {
            MessageComponentInteraction messageComponentInteraction = event.getMessageComponentInteraction();
            String customId = messageComponentInteraction.getCustomId();
            switch (customId) {
                case "buteB":
                    messageComponentInteraction.acknowledge();
                    //messageComponentInteraction.getMessage().removeEmbed();
                    messageComponentInteraction.getMessage().edit(new EmbedBuilder().setImage(new File("emptybuttonloadingbar.png")));
                    break;
            }
        });



        api.addSlashCommandCreateListener(event -> {
            SlashCommandInteraction slashCommandInteraction = event.getSlashCommandInteraction();
            if (slashCommandInteraction.getCommandName().equals("bute")) {
                slashCommandInteraction.respondLater().thenAccept(interactionOriginalResponseUpdater -> {
                    interactionOriginalResponseUpdater.addEmbed(new EmbedBuilder().setImage(new File("emptybuttonloadingbar.png"))).addComponents(ActionRow.of(Button.success("buteB", "Bute"))).update();
                    currentRank = "emptybuttonloadingbar.png";

                    Thread thread = new Thread(){
                        public void run(){
                            while (true) {
                                int randTime = (int) (Math.random() * 20 + 20);
                                try {
                                    TimeUnit.SECONDS.sleep(10);
                                } catch (InterruptedException ie) {
                                    Thread.currentThread().interrupt();
                                }
                                interactionOriginalResponseUpdater.removeAllEmbeds().addEmbed(new EmbedBuilder().setImage(new File(getNextRank()))).update();
                                currentRank = getNextRank();
                            }
                        }
                    };

                    thread.start();


                });
            }
        });

//        api.addMessageComponentCreateListener(event -> {
//            MessageComponentInteraction messageComponentInteraction = event.getMessageComponentInteraction();
//            String customId = messageComponentInteraction.getCustomId();
//
//            if (customId.equals("buteB")) {
//                messageComponentInteraction.createImmediateResponder()
//                        .setContent("You clicked a button!")
//                        .respond();
//                messageComponentInteraction.respondLater().thenAccept(interactionOriginalResponseUpdater -> {
//                    interactionOriginalResponseUpdater.addEmbed(new EmbedBuilder().setImage(new File("emptybuttonloadingbar.png"))).addComponents(ActionRow.of(Button.success("buteB", "Bute"))).update();
//                });
//                currentRank = "emptybuttonloadingbar.png";
//            }
//        });

        System.out.println(api.createBotInvite());
    }

    public static String getNextRank() {
        String res = "";
        if (currentRank.equals("emptybuttonloadingbar.png"))
            res = "purpleraisinrank.png";
        else if (currentRank.equals("purpleraisinrank.png"))
            res = "blueballs.png";
        else if (currentRank.equals("blueballs.png"))
            res = "tealrock.png";
        else if (currentRank.equals("tealrock.png"))
            res = "greenbooger.png";
        else if (currentRank.equals("greenbooger.png"))
            res = "yelloweggyolk.png";
        else if (currentRank.equals("yelloweggyolk.png"))
            res = "lightbrownstrandofhair.png";
        else if (currentRank.equals("lightbrownstrandofhair.png"))
            res = "brownskidmark.png";
        else if (currentRank.equals("brownskidmark.png"))
            res = "magenta.png";
        else if (currentRank.equals("magenta.png"))
            res = "punchpink.png";
        else if (currentRank.equals("punchpink.png"))
            res = "tangerineorange.png";
        else if (currentRank.equals("tangerineorange.png"))
            res = "tangerineorange.png";
        return res;
    }

    public static String getRole() {
        String res = "";
        if (currentRank.equals("purpleraisinrank.png"))
            res = "1131052677879574539";
        else if (currentRank.equals("blueballs.png"))
            res = "1131052659785338951";
        else if (currentRank.equals("tealrock.png"))
            res = "1131052625253650462";
        else if (currentRank.equals("greenbooger.png"))
            res = "1131052643775692920";
        else if (currentRank.equals("yelloweggyolk.png"))
            res = "1131052607415263323";
        else if (currentRank.equals("lightbrownstrandofhair.png"))
            res = "1131052517195780177";
        else if (currentRank.equals("brownskidmark.png"))
            res = "1131052491828641792";
        else if (currentRank.equals("magenta.png"))
            res = "1131052394353000570";
        else if (currentRank.equals("punchpink.png"))
            res = "1131052362597945515";
        else if (currentRank.equals("tangerineorange.png"))
            res = "1131052270109335553";
        return res;
    }

}