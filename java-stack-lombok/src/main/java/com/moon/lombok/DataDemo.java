package com.moon.lombok;

import lombok.Data;

/**
 * Data 注解 Demo
 *
 * @author MoonZero
 * @version 1.0
 * @date 2019-11-7 09:07
 * @description `@Data` 注解与同时使用以下的注解的效果是一样的
 * <p>`@ToString`</p>
 * <p>`@Getter`</p>
 * <p>`@Setter`</p>
 * <p>`@RequiredArgsConstructor`</p>
 * <p>`@EqualsAndHashCode`</p>
 */
@Data
public class DataDemo {
    private Long id;
    private String summary;
    private String description;
}
