package org.example.N_PLUS_1_SOLUTION.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee_chats")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeChats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @JoinColumn(name = "chat_id")
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Chat chat;
    @JoinColumn(name = "employee_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MyEmployee employee;

    public void addEmployeeToChatAndChatToEmployee( Chat chat, MyEmployee employee){
        this.chat = chat;
        chat.getListOfEmployeesChats().add(this);

        this.employee = employee;
        employee.getListOfEmployeeChats().add(this);
    }
}
