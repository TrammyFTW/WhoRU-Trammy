package com.uic.whoru;

public class WhoRU {

    public final int SIZE = 70;
    String[] questions;
    String[] answers;
    String[][] choices;

    public static int count = 0;
    public static int currentQuestion = 0;

    public WhoRU() {
        questions = new String[SIZE];
        answers = new String[SIZE];
        choices = new String[SIZE][SIZE];

        initialize();
    }

    public String getQuestion(int index){
        return questions[index];
    }

    public String getChoice(int index, int choice){
        return choices[index][choice];
    }

    public String getAnswer(int index){
        return answers[index];
    }

    public boolean isExtrovert(){
        int[] items = {1,8,15,22,29,36,43,50,57,64};
        int E = 0;
        int I = 0;
        for(int i=0;i<items.length;i++){
            if(answers[items[i]-1]=="A")E++;
            else I++;
        }
        return (E>I) ? true : false;
    }

    public boolean isSensing(){
        int[] items = {2,9,16,23,30,37,44,51,58,65,3,10,17,24,31,38,45,52,59,66};
        int S = 0;
        int N = 0;
        for(int i=0;i<items.length;i++){
            if(answers[items[i]-1]=="A")S++;
            else N++;
        }
        return (S>N) ? true : false;
    }

    public boolean isThinking(){
        int[] items = {4,11,18,25,32,39,46,53,60,67,5,12,19,26,33,40,47,54,61,68};
        int T = 0;
        int F = 0;
        for(int i=0;i<items.length;i++){
            if(answers[items[i]-1]=="A")T++;
            else F++;
        }
        return (T>F) ? true : false;
    }

    public boolean isJudging(){
        int[] items = {6,13,20,27,34,41,48,55,62,69,7,14,21,28,35,42,49,56,63,70};
        int J = 0;
        int P = 0;
        for(int i=0;i<items.length;i++){
            if(answers[items[i]-1]=="A")J++;
            else P++;
        }
        return (J>P) ? true : false;
    }

    public void initialize(){
        questions[0] = "At a party do you?";
        choices[0][0] = "Interacting with many, including strangers";
        choices[0][1] = "Interact with a few, known to you";

        questions[1] = "Are you more?";
        choices[1][0] = "Realistic";
        choices[1][1] = "Philosophically inclined";

        questions[2] = "Are you more intrigued by?";
        choices[2][0] = "Facts";
        choices[2][1] = "comparing similarities";

        questions[3] = "Are you usually more?";
        choices[3][0] = "Fair minded";
        choices[3][1] = "Kind hearted";

        questions[4] = "Do you tend to be more?";
        choices[4][0] = "Dispassionate";
        choices[4][1] = "Sympathetic";

        questions[5] = "Do you prefer to work?";
        choices[5][0] = "To deadlines";
        choices[5][1] = "Just \"whenever\"";

        questions[6] = "Do you tend to choose?";
        choices[6][0] = "Rather carefully";
        choices[6][1] = "Somewhat impulsively";

        questions[7] = "At parties do you?";
        choices[7][0] = "Stay late, with increasing energy";
        choices[7][1] = "Leave early with decreasing energy";

        questions[8] = "Are you a more?";
        choices[8][0] = "Sensible person";
        choices[8][1] = "Reflective person";

        questions[9] = "Are you more drawn to?";
        choices[9][0] = "Hard data";
        choices[9][1] = "Complicated ideas";

        questions[10] = "Is it more natural for you to be?";
        choices[10][0] = "Fair to others";
        choices[10][1] = "Nice to others";

        questions[11] = "In first approaching others are you more?";
        choices[11][0] = "Impersonal and detached";
        choices[11][1] = "Personal and engaging";

        questions[12] = "Are you usually more?";
        choices[12][0] = "Punctual";
        choices[12][1] = "Leisurely";

        questions[13] = "Does it bother you more having things?";
        choices[13][0] = "Incomplete";
        choices[13][1] = "Completed";

        questions[14] = "In your social groups do you?";
        choices[14][0] = "Keep abreast of others happenings";
        choices[14][1] = "Get behind on the news";

        questions[15] = "Are you usually more interested in?";
        choices[15][0] = "Specifics";
        choices[15][1] = "Concepts";

        questions[16] = "Do you prefer writers who?";
        choices[16][0] = "Say what they mean";
        choices[16][1] = "Use lots of analogies";

        questions[17] = "Are you more naturally?";
        choices[17][0] = "Impartial";
        choices[17][1] = "Compassionate";

        questions[18] = "In judging are you more likely to be?";
        choices[18][0] = "Impersonal";
        choices[18][1] = "Sentimental";

        questions[19] = "Do you usually?";
        choices[19][0] = "Settle things";
        choices[19][1] = "Keep options open";

        questions[20] = "Are you usually rather?";
        choices[20][0] = "Quick to agree to a time";
        choices[20][1] = "Reluctant to agree to a time";

        questions[21] = "In phoning do you?";
        choices[21][0] = "Just start talking";
        choices[21][1] = "Rehearse what you'll say";

        questions[22] = "Facts?";
        choices[22][0] = "Speak for themselves";
        choices[22][1] = "Usually require interpretation";

        questions[23] = "Do you prefer to work with?";
        choices[23][0] = "Practical information";
        choices[23][1] = "Abstract ideas";

        questions[24] = "Are you included to be more?";
        choices[24][0] = "Cool headed";
        choices[24][1] = "Warm hearted";

        questions[25] = "Would you rather be?";
        choices[25][0] = "More than just merciful";
        choices[25][1] = "More merciful than just";

        questions[26] = "Are you more comfortable with?";
        choices[26][0] = "Setting a schedule";
        choices[26][1] = "Putting things off";

        questions[27] = "Are you more comfortable with?";
        choices[27][0] = "Written agreements";
        choices[27][1] = "Handshake agreements";

        questions[28] = "In company do you?";
        choices[28][0] = "Start conversations";
        choices[28][1] = "Wait to be approached";

        questions[29] = "Traditional common sense is?";
        choices[29][0] = "Usually trustworthy";
        choices[29][1] = "often misleading";

        questions[30] = "Children often do not?";
        choices[30][0] = "Make themselves useful enough";
        choices[30][1] = "Daydream enough";

        questions[31] = "Are you usually more?";
        choices[31][0] = "Tough minded";
        choices[31][1] = "Tender hearted";

        questions[32] = "Are you more?";
        choices[32][0] = "Firm than gentle";
        choices[32][1] = "Gentle than firm";

        questions[33] = "Are you more prone to keep things?";
        choices[33][0] = "well organized";
        choices[33][1] = "Open-ended";

        questions[34] = "Do you put more value on the?";
        choices[34][0] = "Definite";
        choices[34][1] = "Variable";

        questions[35] = "Does new interaction with others?";
        choices[35][0] = "Stimulate and energize you";
        choices[35][1] = "Tax your reserves";

        questions[36] = "Are you more frequently?";
        choices[36][0] = "A practical sort of a person";
        choices[36][1] = "An abstract sort of a person";

        questions[37] = "Which are you drawn to?";
        choices[37][0] = "Accurate perception";
        choices[37][1] = "Concept formation";

        questions[38] = "Which is more satisfying?";
        choices[38][0] = "To discuss an issue throughly";
        choices[38][1] = "To arrive at agreement on an issue";

        questions[39] = "Which rules do you more?";
        choices[39][0] = "Your head";
        choices[39][1] = "Your heart";

        questions[40] = "Are you more comfortable with work?";
        choices[40][0] = "Contracted";
        choices[40][1] = "Done on a casual basis";

        questions[41] = "Do you prefer things to be?";
        choices[41][0] = "Neat and orderly";
        choices[41][1] = "Optional";

        questions[42] = "Do you prefer?";
        choices[42][0] = "Many friends with brief contact";
        choices[42][1] = "A few friends with longer contact";

        questions[43] = "Are you more drawn to?";
        choices[43][0] = "Substantial information";
        choices[43][1] = "Credible assumptions";

        questions[44] = "Are you more interested in?";
        choices[44][0] = "Production";
        choices[44][1] = "Research";

        questions[45] = "Are you more comfortable when you are?";
        choices[45][0] = "Objective";
        choices[45][1] = "Personal";

        questions[46] = "Do you value in yourself more that you are?";
        choices[46][0] = "Unwavering";
        choices[46][1] = "Devoted";

        questions[47] = "Are you more comfortable with?";
        choices[47][0] = "Final statements";
        choices[47][1] = "Tentative statements";

        questions[48] = "Are you more comfortable?";
        choices[48][0] = "After a decision";
        choices[48][1] = "Before a decision";

        questions[49] = "Do you?";
        choices[49][0] = "Speak easily and at length with strangers";
        choices[49][1] = "Find little to say to strangers";

        questions[50] = "Are you usually more interested in the?";
        choices[50][0] = "Particular instance";
        choices[50][1] = "General case";

        questions[51] = "Do you feel?";
        choices[51][0] = "More practical then ingenious";
        choices[51][1] = "More ingenious than practical";

        questions[52] = "Are you typically more a person of?";
        choices[52][0] = "Clear reason";
        choices[52][1] = "Strong feeling";

        questions[53] = "Are you inclined more to be?";
        choices[53][0] = "Fair-minded";
        choices[53][1] = "Sympathetic";

        questions[54] = "Is it preferable mostly to?";
        choices[54][0] = "Make sure things are arranged";
        choices[54][1] = "Just let things happen";

        questions[55] = "Is it your way more to?";
        choices[55][0] = "Get things settled";
        choices[55][1] = "Put off settlement";

        questions[56] = "When the phone rings do you?";
        choices[56][0] = "Quickly get to it first";
        choices[56][1] = "Hope someone else will answer";

        questions[57] = "Do you prize more in yourself a?";
        choices[57][0] = "Good sense of reality";
        choices[57][1] = "Good imagination";

        questions[58] = "Are you drawn more to?";
        choices[58][0] = "Fundamentals";
        choices[58][1] = "Hidden meanings";

        questions[59] = "In judging are you usually more?";
        choices[59][0] = "Neutral";
        choices[59][1] = "Charitable";

        questions[60] = "Are you convinced by?";
        choices[60][0] = "Evidence";
        choices[60][1] = "Someone you trust";

        questions[61] = "Do you prefer?";
        choices[61][0] = "Getting something done";
        choices[61][1] = "Having the option to go back";

        questions[62] = "Do you prefer to?";
        choices[62][0] = "Set things up perfectly";
        choices[62][1] = "Allow some things to just come together";

        questions[63] = "At a networking function are you?";
        choices[63][0] = "Easy to approach";
        choices[63][1] = "A little reserved";

        questions[64] = "Do you have more fun with?";
        choices[64][0] = "Hands-on experience";
        choices[64][1] = "Blue-sky fantasy";

        questions[65] = "In writings do you prefer?";
        choices[65][0] = "The more literal";
        choices[65][1] = "The more figurative";

        questions[66] = "Are you usually more?";
        choices[66][0] = "Unbiased";
        choices[66][1] = "compassionate";

        questions[67] = "Are you typically more?";
        choices[67][0] = "Just than lenient";
        choices[67][1] = "Lenient than just";

        questions[68] = "Is it more like you to?";
        choices[68][0] = "Make snap judgements";
        choices[68][1] = "Delay making judgements";

        questions[69] = "Do you tend to be more?";
        choices[69][0] = "Deliberate than spontaneous";
        choices[69][1] = "Spontaneous than deliberate";

    }

    /*
    * From C. Myers and I. M. Briggs
    *
    * Personalities are based on the following simple traits:
        Whether we motivate ourselves (Introvert) or are motivated by others (Extrovert)
        Whether we gather facts (Sensing) or trust our gut (Intuiting) when gathering information
        Whether we make decisions by studying facts (Thinking) or by trusting experts that we feel are honest (Feeling)
        Whether we prefer getting the job done (Judging) or following a plan (Perceiving)
    *
    * 1,8,15,22,29,36,43,50,57,64
    * E - Extrovert (A)
    * I - Introvert (B)
    *
    * 2,9,16,23,30,37,44,51,58,65
    * 3,10,17,24,31,38,45,52,59,66
    * S - Sensing (A)
    * N - Intuiting (B)
    *
    * 4,11,18,25,32,39,46,53,60,67
    * 5,12,19,26,33,40,47,54,61,68
    * T - Thinking (A)
    * F - Feeling (B)
    *
    * 6,13,20,27,34,41,48,55,62,69
    * 7,14,21,28,35,42,49,56,63,70
    * J - Judging(A)
    * P - Perceiving(B)
    *
    * */

}
