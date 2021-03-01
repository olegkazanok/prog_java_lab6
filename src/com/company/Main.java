package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

class MainFrame extends JFrame {

        //Константы, задающие размер окна приложения, если оно
        //не распахнуто на весь экран
        private static final int WIDTH = 700;
        private static final int HEIGHT = 500;

        private JMenuItem pauseMenuItem;
        private JMenuItem resumeMenuItem;

        //поле, по которому прыгают мячи
        private Field field = new Field();

        //Конструктор главного окна приложения
        public MainFrame(){
            super("Программирование и синхронизация потоков");
            setSize(WIDTH, HEIGHT);
            Toolkit kit = Toolkit.getDefaultToolkit();
            //Отцентрировать окно приложения на экране
            setLocation((kit.getScreenSize().width - WIDTH)/2,(kit.getScreenSize().height - HEIGHT)/2);
            //Установить начальное состояние окна развёрнутым на весь экран
            setExtendedState(MAXIMIZED_BOTH);

            //Создать меню
            JMenuBar menuBar = new JMenuBar();
            setJMenuBar(menuBar);
            JMenu ballMenu = new JMenu("Мячи");
            Action addBallAction = new AbstractAction("добавить мяч") {
                public void actionPerformed(ActionEvent e) {
                    field.addBall();
                    if(!pauseMenuItem.isEnabled() && !resumeMenuItem.isEnabled()){
                        //Ни один из пунктов меню не является
                        //доступными - сделать доступным "Паузу"
                        pauseMenuItem.setEnabled(true);
                    }
                }
            };
            menuBar.add(ballMenu);
            ballMenu.add(addBallAction);
            JMenu controlMenu = new JMenu("Управление");
            menuBar.add(controlMenu);
            Action pauseAction = new AbstractAction("Приостановить движение") {
                public void actionPerformed(ActionEvent event) {
                    field.pause();
                    pauseMenuItem.setEnabled(false);
                    resumeMenuItem.setEnabled(true);
                }
            };
            pauseMenuItem = controlMenu.add(pauseAction);
            pauseMenuItem.setEnabled(false);
            Action resumeAction = new AbstractAction("Возобновить движение") {
                public void actionPerformed(ActionEvent event) {
                    field.resume();
                    pauseMenuItem.setEnabled(true);
                    resumeMenuItem.setEnabled(false);
                }
            };
            resumeMenuItem = controlMenu.add(resumeAction);
            resumeMenuItem.setEnabled(false);
            //Добавить в центр граничной компоновки поле Field
            getContentPane().add(field, BorderLayout.CENTER);
        }
        //Главный метод приложения
        public static void main(String[] args){
        //Создать и  сделать видимым главное окно приложения
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
