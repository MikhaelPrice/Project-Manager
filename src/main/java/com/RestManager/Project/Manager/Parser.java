package com.RestManager.Project.Manager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public static void parsFile(String text, String place, String replacer) {
        Pattern pattern = Pattern.compile(place);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            text = text.replace(place, replacer);
        }
        System.out.println(text);
    }

    public static void main(String[] args) {
        parsFile("<template>\n" +
                "    <v-app>\n" +
                "        <v-toolbar app>\n" +
                "            <v-toolbar-title>Sarafan</v-toolbar-title>\n" +
                "            <v-btn flat\n" +
                "                   v-if=\"profile\"\n" +
                "                   :disabled=\"$route.path === '/'\"\n" +
                "                   @click=\"showMessages\">\n" +
                "                Messages\n" +
                "            </v-btn>\n" +
                "            <v-spacer></v-spacer>\n" +
                "            <v-btn flat\n" +
                "                   v-if=\"profile\"\n" +
                "                   :disabled=\"$route.path === '/profile'\"\n" +
                "                   @click=\"showProfile\">\n" +
                "                {{profile.name}}\n" +
                "            </v-btn>\n" +
                "            <v-btn v-if=\"profile\" icon href=\"/logout\">\n" +
                "                <v-icon>exit_to_app</v-icon>\n" +
                "            </v-btn>\n" +
                "        </v-toolbar>\n" +
                "        <v-content>\n" +
                "            <router-view></router-view>\n" +
                "        </v-content>\n" +
                "    </v-app>\n" +
                "</template>\n" +
                "\n" +
                "<script>\n" +
                "    import { mapState, mapMutations } from 'vuex'\n" +
                "    import { addHandler } from 'util/ws'\n" +
                "    export default {\n" +
                "        computed: mapState(['profile']),\n" +
                "        methods: {\n" +
                "            ...mapMutations(['addMessageMutation', 'updateMessageMutation', 'removeMessageMutation']),\n" +
                "            showMessages() {\n" +
                "                this.$router.push('/')\n" +
                "            },\n" +
                "            showProfile() {\n" +
                "                this.$router.push('/profile')\n" +
                "            }\n" +
                "        },\n" +
                "        created() {\n" +
                "            addHandler(data => {\n" +
                "                if (data.objectType === 'MESSAGE') {\n" +
                "                    switch (data.eventType) {\n" +
                "                        case 'CREATE':\n" +
                "                            this.addMessageMutation(data.body)\n" +
                "                            break\n" +
                "                        case 'UPDATE':\n" +
                "                            this.updateMessageMutation(data.body)\n" +
                "                            break\n" +
                "                        case 'REMOVE':\n" +
                "                            this.removeMessageMutation(data.body)\n" +
                "                            break\n" +
                "                        default:\n" +
                "                            console.error(`Looks like the event type if unknown \"${data.eventType}\"`)\n" +
                "                    }\n" +
                "                } else {\n" +
                "                    console.error(`Looks like the object type if unknown \"${data.objectType}\"`)\n" +
                "                }\n" +
                "            })\n" +
                "        },\n" +
                "        beforeMount() {\n" +
                "            if (!this.profile) {\n" +
                "                this.$router.replace('/auth')\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "</script>\n" +
                "\n" +
                "<style>\n" +
                "</style>", "Message", "Project");
    }

}
