# ChatBot

At first I've tried to figure out how the protocol questions location should be implemented with regards to memory consuption and efficency.
Eventually I went with the understanding that the entire conversation is present in a single protocol but I thought of several other options:
1. Split the protocol to different files and load all the questions from this files - This is duable and will make each part of the protocal smaller as there will be less questions in each file but might make the entire protocol less readable.
2. Split the protocol to different files and load only the needed protocol - This will reduce memory consumption as only necessary parts of the protocol will be loaded to the memory. On the other hand, it will make each protocol more complex as an indication to load a new protocol must be available.
3. I also thought about spliting the conversation for the sake of reuse conversation flows that might reoccur in different protocols. I decided against it as I figured that this will force each change in the flow to all related protocols and this changes might be irrelevant for other protocols.

In regards to the conversation flow. I've decided that the questions list will not determine the questions order as I think it is more flexible that each question points to its following questio, as well as each answer. It also gives a very easy way to add questions to the flow and even entire questions flow.
The questions type I've listed are "open","single" and "multiple" as this are defined in the spec. With that said, I did think of many other question types and decided they are out of the scope for this exercise. One type I thought of was a question that can accept several of the possible answers but not all of them or a question that one answer makes the other obsulete. I've thought about a way to represent this type of questions with a generic format and it seemed to overcomplicate each question and answer data structure. The implemetation allows to questions type to override the load operation so adding questions with specific attributes is easy.

The engine just outputs the conversation but it is saved in a json format so it can be transfered or saved for other use cases as I wasn't sure what is the exact requirement.

Regarding answers identifier - I used the lowercase of the text for simplicity as I don't think the engine itself should handle the answer validation but a complete seperate component and I wasn't convinced this is important for the specific exercise.
