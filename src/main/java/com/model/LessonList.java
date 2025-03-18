package com.model;

import java.util.ArrayList;

public class LessonList {
    private static LessonList lessonListInstance = null;
    ArrayList<LessonList> Lessonlist;
    ArrayList<Lesson> lessons;

    private LessonList(){
        lessons = new ArrayList<>();
    }

    public static LessonList getInstance(){
        if (lessonListInstance == null){
            lessonListInstance = new LessonList();
        }
        return lessonListInstance;
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    public ArrayList<Lesson> getLesson(String keyword) {
        ArrayList<Lesson> result = new ArrayList<>();
        for (Lesson lesson : lessons) {
            if (lesson.getTopic().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(lesson);
            }
        }
        return result;
    }


    
}
