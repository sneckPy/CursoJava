package py.com.ci.person.boundary;

import py.com.ci.person.entities.Person;
import py.com.ci.util.ConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonManager {

    private final String CREATE_STMT = " INSERT INTO public.people(person_name,person_lastname,person_phone, " +
            "person_email, person_address) VALUES(?,?,?,?,?) ";
    private final String READ_STMT = " SELECT person_id,person_name,person_lastname,person_phone,person_email,person_address " +
            " FROM public.people ";
    private final String UPDATE_STMT = " UPDATE public.people SET person_name=?,person_lastname=?,person_phone=?, " +
            " person_email=?, person_address=? WHERE person_id=? ";
    private final String DELETE_STMT = " DELETE FROM public.people where person_id=? ";

    public boolean addPerson(Person person) {
        try (PreparedStatement s1 = ConnectionManager.getConnection().prepareStatement(CREATE_STMT)) {
            s1.setString(1, person.getPersonName());
            s1.setString(2, person.getPersonLastname());
            s1.setString(3, person.getPersonPhone());
            s1.setString(4, person.getPersonEmail());
            s1.setString(5, person.getPersonAddress());
            s1.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public List<Person> getAllPeople() {
        List<Person> personList = new ArrayList<>();
        try (PreparedStatement s1 = ConnectionManager.getConnection().prepareStatement(READ_STMT)) {
            s1.setMaxRows(100);
            try (ResultSet rs = s1.executeQuery()) {
                while (rs.next()) {
                    personList.add(getFromRsPerson(rs));
                }
                return personList;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public boolean updatePerson(Person person ){
        try (PreparedStatement s1 = ConnectionManager.getConnection().prepareStatement(UPDATE_STMT)){
            s1.setString(1, person.getPersonName());
            s1.setString(2, person.getPersonLastname());
            s1.setString(3, person.getPersonPhone());
            s1.setString(4, person.getPersonEmail());
            s1.setString(5, person.getPersonAddress());
            s1.setInt(6, person.getPersonId());
            s1.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public boolean deletePerson(Person person){
        try (PreparedStatement s1= ConnectionManager.getConnection().prepareStatement(DELETE_STMT)){
            s1.setInt(1,person.getPersonId());
            s1.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    private Person getFromRsPerson(ResultSet rs) {
        try (PreparedStatement s1 = ConnectionManager.getConnection().prepareStatement(READ_STMT)) {
            Person person = new Person();
            person.setPersonId(rs.getInt(1));
            person.setPersonName(rs.getString(2));
            person.setPersonLastname(rs.getString(3));
            person.setPersonPhone(rs.getString(4));
            person.setPersonEmail(rs.getString(5));
            person.setPersonAddress(rs.getString(6));
            return person;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

}
