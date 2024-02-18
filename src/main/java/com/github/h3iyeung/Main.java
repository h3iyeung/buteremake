package com.github.h3iyeung;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.intent.Intent;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.component.ActionRow;
import org.javacord.api.entity.message.component.Button;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.user.User;
import org.javacord.api.interaction.MessageComponentInteraction;
import org.javacord.api.interaction.SlashCommand;
import org.javacord.api.interaction.SlashCommandBuilder;
import org.javacord.api.interaction.SlashCommandInteraction;

import java.io.File;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Main {

    static String empty = "https://raw.githubusercontent.com/h3iyeung/buteremake/master/emptybuttonloadingbar.png";
    static String purple = "https://raw.githubusercontent.com/h3iyeung/buteremake/master/purpleraisinrank.png";
    static String blue = "https://raw.githubusercontent.com/h3iyeung/buteremake/master/blueballs.png";
    static String teal = "https://raw.githubusercontent.com/h3iyeung/buteremake/master/tealrock.png";
    static String green = "https://raw.githubusercontent.com/h3iyeung/buteremake/master/greenbooger.png";
    static String yellow = "https://raw.githubusercontent.com/h3iyeung/buteremake/master/yelloweggyolk.png";
    static String lightBrown = "https://raw.githubusercontent.com/h3iyeung/buteremake/master/lightbrownstrandofhair.png";
    static String brown = "https://raw.githubusercontent.com/h3iyeung/buteremake/master/brownskidmark.png";
    static String magenta = "https://raw.githubusercontent.com/h3iyeung/buteremake/master/magenta.png";
    static String punchPink = "https://raw.githubusercontent.com/h3iyeung/buteremake/master/punchpink.png";
    static String tangerine = "https://raw.githubusercontent.com/h3iyeung/buteremake/master/tangerineorange.png";

    static String currentRank = empty;

    public static void main(String[] args) {
        DiscordApi api = new DiscordApiBuilder()
                .setToken("token")
                .addIntents(Intent.GUILD_MEMBERS)
                .login()
                .join();


        Set<SlashCommandBuilder> builders = new HashSet<>();
        builders.add(SlashCommand.with("bute", "creates the bute"));

        api.bulkOverwriteGlobalApplicationCommands(builders).join();


        api.addMessageComponentCreateListener(event -> {
            MessageComponentInteraction messageComponentInteraction = event.getMessageComponentInteraction();
            String customId = messageComponentInteraction.getCustomId();
            switch (customId) {
                case "buteB":
                    Optional<Role> rank  = api.getRoleById(getRole());
                    Optional<TextChannel> updates = api.getTextChannelById("1131080235132145715");
                    messageComponentInteraction.acknowledge();
                    messageComponentInteraction.getMessage().edit(new EmbedBuilder().setImage(empty));
                    if(rank.isPresent())
                        messageComponentInteraction.getUser().addRole(rank.get());
                    if(updates.isPresent()&&rank.isPresent()) {
                        if(rank.get().getName().equals("blue balls"))
                            new MessageBuilder().append(messageComponentInteraction.getUser().getMentionTag() + " now has " + rank.get().getMentionTag()).send(updates.get());
                        else if(rank.get().getName().equals("tangerine orange hair dye")) {
                            User[] members = messageComponentInteraction.getServer().get().getMembers().toArray(new User[messageComponentInteraction.getServer().get().getMembers().size()]);
                            for(int i =0; i <members.length; i++){
                                Role[] userRoles = members[i].getRoles(messageComponentInteraction.getServer().get()).toArray(new Role[members[i].getRoles(messageComponentInteraction.getServer().get()).size()]);
                                for(int h =0; h<userRoles.length; h ++){
                                    if(userRoles[h].getName().equals("tangerine orange hair dye")&&api.getRoleById("1131052270109335553").isPresent())
                                        members[i].removeRole(api.getRoleById("1131052270109335553").get());
                                }
                            }
                            new MessageBuilder().append(messageComponentInteraction.getUser().getMentionTag() + " now has a " + rank.get().getMentionTag()).send(updates.get());
                        }
                        else
                            new MessageBuilder().append(messageComponentInteraction.getUser().getMentionTag() + " now has a " + rank.get().getMentionTag()).send(updates.get());
                    }
                    currentRank = empty;
                    break;
            }
        });

        api.addSlashCommandCreateListener(event -> {
            SlashCommandInteraction slashCommandInteraction = event.getSlashCommandInteraction();
            if (slashCommandInteraction.getCommandName().equals("bute")) {
                slashCommandInteraction.respondLater().thenAccept(interactionOriginalResponseUpdater -> {
                    interactionOriginalResponseUpdater.addEmbed(new EmbedBuilder().setImage(empty)).addComponents(ActionRow.of(Button.success("buteB", "Bute"))).update();
                    currentRank = empty;

                    Thread thread = new Thread(){
                        public void run(){
                            while (true) {
                                int randTime = (int) (Math.random() * 21600 + 1);
                                try {
                                    TimeUnit.SECONDS.sleep(randTime);
                                } catch (InterruptedException ie) {
                                    Thread.currentThread().interrupt();
                                }
                                try {
                                    api.getMessageById("1208181083754074143",api.getTextChannelById("1130933983002243185").get()).get().removeEmbed();
                                    api.getMessageById("1208181083754074143",api.getTextChannelById("1130933983002243185").get()).get().edit(new EmbedBuilder().setImage(getNextRank()));
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                } catch (ExecutionException e) {
                                    throw new RuntimeException(e);
                                }
                                currentRank = getNextRank();
                            }
                        }
                    };

                    thread.start();


                });
            }
        });


        System.out.println(api.createBotInvite());
    }

    public static String getNextRank() {
        String res = "";
        if (currentRank.equals(empty))
            res = purple;
        else if (currentRank.equals(purple))
            res = blue;
        else if (currentRank.equals(blue))
            res = teal;
        else if (currentRank.equals(teal))
            res = green;
        else if (currentRank.equals(green))
            res = yellow;
        else if (currentRank.equals(yellow))
            res = lightBrown;
        else if (currentRank.equals(lightBrown))
            res = brown;
        else if (currentRank.equals(brown))
            res = magenta;
        else if (currentRank.equals(magenta))
            res = punchPink;
        else if (currentRank.equals(punchPink))
            res = tangerine;
        else if (currentRank.equals(tangerine))
            res = tangerine;
        return res;
    }

    public static String getRole() {
        String res = "";
        if (currentRank.equals(purple))
            res = "1131052677879574539";
        else if (currentRank.equals(blue))
            res = "1131052659785338951";
        else if (currentRank.equals(teal))
            res = "1131052625253650462";
        else if (currentRank.equals(green))
            res = "1131052643775692920";
        else if (currentRank.equals(yellow))
            res = "1131052607415263323";
        else if (currentRank.equals(lightBrown))
            res = "1131052517195780177";
        else if (currentRank.equals(brown))
            res = "1131052491828641792";
        else if (currentRank.equals(magenta))
            res = "1131052394353000570";
        else if (currentRank.equals(punchPink))
            res = "1131052362597945515";
        else if (currentRank.equals(tangerine))
            res = "1131052270109335553";
        return res;
    }

}