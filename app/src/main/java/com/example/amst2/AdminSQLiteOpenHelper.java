package com.example.amst2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    //Se establece la base de datos SQLite con un archivo java llamado AdminSQLiteOpenHelper
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name,
                                 @Nullable SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        //Se crea la tabla libros en la base de datos con los campos necesarios
        sqLiteDatabase.execSQL("create table libro(id int primary key,titulo text, autor text, editorial text, descripcion text, categoria text)");
        sqLiteDatabase.execSQL("insert into libro(id,titulo,autor,editorial,descripcion, categoria) values(1,'Los detectives salvajes','Roberto Bolaño','DEBOLSILLO','Fotógrafos, toreros, estudiantes, prostitutas, pistoleros, un neonazi…son algunos de los personajes que acompañaran a los dos protagonistas a lo largo de su viaje de no retorno hacia una nueva realidad.','Aventura')");
        sqLiteDatabase.execSQL("insert into libro(id,titulo,autor,editorial,descripcion, categoria) values(2,'Dune','Frank Herbert','DEBOLSILLO','Este clásico de la ciencia ficción describe, construye y da vida a diferentes sociedades interplanetarias, que reflejan los comportamientos y las actitudes del ser humano en un futuro muy lejano, a más de 10.000 años de nuestra época.','Ficción')");
        sqLiteDatabase.execSQL("insert into libro(id,titulo,autor,editorial,descripcion, categoria) values(3,'El principito','Antoine de Saint-Exupery','SALAMANDRA','Fábula mítica y relato filosófico que interroga acerca de la relación del ser humano con su prójimo y con el mundo, El Principito concentra, con maravillosa simplicidad, la constante reflexión de Saint-Exupery sobre la amistad, el amor, la responsabilidad y el sentido de la vida.','Infantil')");
        sqLiteDatabase.execSQL("insert into libro(id,titulo,autor,editorial,descripcion, categoria) values(4,'Bajo un cielo escarlata','Mark Sullivan','SUMA','Bajo un cielo escarlata es un libro basado en hechos reales de Mark Sullivan. En medio de la Segunda Guerra Mundial, Pino Lella, un joven italiano, se acabará convirtiendo en un héroe, que a nuestros ojos quedaría olvidado si no fuera por estas páginas','Aventura')");
        sqLiteDatabase.execSQL("insert into libro(id,titulo,autor,editorial,descripcion, categoria) values(5,'Harry Potter y la Piedra Filosofal','JK Rowling','SALAMANDRA','Harry Potter y la piedra filosofal es el primer volumen de la ya clásica serie de novelas fantásticas de la autora británica J.K. Rowling.','Ficción')");
        sqLiteDatabase.execSQL("insert into libro(id,titulo,autor,editorial,descripcion, categoria) values(6,'Matilda','Roald Dahl','Alfaguara','Matilda es una lectora de solo cinco años. Sensible e inteligente, todos la admiran menos sus mediocres padres, que la consideran una infantil','Infantil')");
        sqLiteDatabase.execSQL("insert into libro(id,titulo,autor,editorial,descripcion, categoria) values(7,'La mujer del reloj','Álvaro Arbina','S.A. EDICIONES B','La historia, situada en Vitoria, transcurre durante el inicio de la guerra de la Independencia (1808-1814). España, Portugal y Reino Unido se enfrentan contra Napoleón y el Primer Imperio de Francia.','Aventura')");
        sqLiteDatabase.execSQL("insert into libro(id,titulo,autor,editorial,descripcion, categoria) values(8,'The Hunger Games','Suzanne Collins','SCHOLASTIC INC','Set in a dark vision of the near future, a terrifying reality TV show is taking place. Twelve boys and girls are forced to appear in a live event called The Hunger Games. There is only one rule: kill or be killed.','Ficción')");
        sqLiteDatabase.execSQL("insert into libro(id,titulo,autor,editorial,descripcion, categoria) values(9,'Charlie y la fábrica de chocolates','Roald Dahl','SANTILLANA LOQUELEO','El señor Wonka ha escondido cinco billetes de oro en sus chocolatinas. Quienes los encuentren serán los afortunados que visiten su magnífica fábrica de chocolate. Charlie tiene la suerte de encontrar uno de ellos.','Infantil')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){

    }


}
