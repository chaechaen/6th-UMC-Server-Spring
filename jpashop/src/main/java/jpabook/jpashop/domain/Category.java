// 카테고리 엔티티

package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jpabook.jpashop.domain.item.Item;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany // 다대다
    @JoinTable(name = "category_item", // 중간 테이블에 있는 카테고리 아이디
            joinColumns = @JoinColumn(name = "category_id"), // 아이템 쪽으로 들어가는 코너를 매핑
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();

    // 같은 엔티티에 대해 연관 관계 매핑 해준 것임
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

//    //==연관관계 메서드==//
//    public void addChildCategory(Category child) {
//
//        this.child.add(child);
//        child.setParent(this);
//    }
}