regExps = {
"exercise_1": /[A-Z][a-z]+/,
"exercise_2": /088[1-9]{7}/,
"exercise_3": /[^10]+/,
"exercise_4": /^[A-Za-z][a-zA-Z0-9._]{2,19}$/,
"exercise_5": /^[19]\d+[09]$/,
"exercise_6": /class=['"].+['"]/
};
cssSelectors = {
"exercise_1": "item > java.class1",
"exercise_2": "different > java",
"exercise_3": "tag.class1:empty, tag.class2:empty",
"exercise_4": "css > item:nth-child(3)",
"exercise_5": "java.class1 + java.class1",
"exercise_6": "item#someId > item > item > item > item",
"exercise_7": "different > different#diffId2 > java:last-child",
"exercise_8": "item#someId"
};
