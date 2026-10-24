package sg.edu.ntu.simple_crm.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@EqualsAndHashCode 
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @NotBlank(message = "First name is mandatory")
  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Email(message = "Email should be valid")
  @Column(name = "email")
  private String email;

  @Column(name = "contact_no")
  private String contactNo;

  @Column(name = "job_title")
  private String jobTitle;

  @Column(name = "year_of_birth")
  private int yearOfBirth;

  public Customer(String firstName, String lastName) {

    this.firstName = firstName;
    this.lastName = lastName;
  }

  public Customer(Long id, String firstName, String lastName,
                String email, String contactNo,
                String jobTitle, int yearOfBirth) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.contactNo = contactNo;
    this.jobTitle = jobTitle;
    this.yearOfBirth = yearOfBirth;
}

}