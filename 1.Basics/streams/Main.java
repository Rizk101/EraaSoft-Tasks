import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);

        List<String> names = Arrays.asList(
                "Ali", "Mona", "Ahmed", "Sara", "Amr", "Laila",
                "Kareem", "Nada", "Nour", "Samy", "", null
        );

        List<Student> students = Arrays.asList(
                new Student("Ali", "IT", 85),
                new Student("Mona", "CS", 92),
                new Student("Ahmed", "IT", 60),
                new Student("Sara", "CS", 70),
                new Student("Omar", "IS", 45),
                new Student("Laila", "IS", 78)
        );

        List<Employee> employees = Arrays.asList(
                new Employee("Ali", 30, "HR", 5000),
                new Employee("Mona", 25, "IT", 7000),
                new Employee("Ahmed", 30, "HR", 5500),
                new Employee("Sara", 27, "IT", 7200),
                new Employee("Omar", 40, "Finance", 8000),
                new Employee("Laila", 35, "Finance", 8200)
        );

        List<List<String>> nestedWords = Arrays.asList(
                Arrays.asList("Java", "Stream"),
                Arrays.asList("API", "Lambda"),
                Arrays.asList("FlatMap", "Map")
        );

        List<Optional<String>> optionalNames = Arrays.asList(
                Optional.of("Ali"),
                Optional.empty(),
                Optional.of("Mona"),
                Optional.empty(),
                Optional.of("Ahmed")
        );

        System.out.println("========= Basic Stream Operations =========");

        List<Integer> evenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("1) Even numbers: " + evenNumbers);

        char letter = 'A';
        List<String> namesStartingWithLetter = names.stream()
                .filter(Objects::nonNull)
                .filter(name -> name.startsWith(String.valueOf(letter)))
                .collect(Collectors.toList());
        System.out.println("2) Names starting with '" + letter + "': " + namesStartingWithLetter);

        List<String> upperCaseNames = names.stream()
                .filter(Objects::nonNull)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("3) Uppercase names: " + upperCaseNames);

        List<Integer> descendingNumbers = numbers.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println("4) Numbers sorted descending: " + descendingNumbers);

        List<Integer> distinctNumbers = numbers.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("5) Distinct numbers: " + distinctNumbers);

        System.out.println("\n========= Intermediate Stream Tasks =========");

        long countLongStrings = names.stream()
                .filter(Objects::nonNull)
                .filter(name -> name.length() > 5)
                .count();
        System.out.println("6) Count of strings longer than 5: " + countLongStrings);

        Optional<Integer> firstGreaterThan5 = numbers.stream()
                .filter(n -> n > 5)
                .findFirst();
        System.out.println("7) First number greater than 5: " + firstGreaterThan5.orElse(null));

        boolean anyDivisibleBy5 = numbers.stream()
                .anyMatch(n -> n % 5 == 0);
        System.out.println("8) Any number divisible by 5? " + anyDivisibleBy5);

        Set<Integer> numbersSet = numbers.stream()
                .collect(Collectors.toSet());
        System.out.println("9) Numbers as Set: " + numbersSet);

        List<Integer> skippedNumbers = numbers.stream()
                .skip(3)
                .collect(Collectors.toList());
        System.out.println("10) After skipping first 3 elements: " + skippedNumbers);

        System.out.println("\n========= Numeric Streams & Reductions =========");

        int sum = numbers.stream()
                .reduce(0, Integer::sum);
        System.out.println("11) Sum of numbers: " + sum);

        Optional<Integer> max = numbers.stream().max(Integer::compareTo);
        Optional<Integer> min = numbers.stream().min(Integer::compareTo);
        System.out.println("12) Max = " + max.orElse(null) + ", Min = " + min.orElse(null));

        OptionalDouble averageGrade = students.stream()
                .mapToDouble(Student::getGrade)
                .average();
        System.out.println("13) Average student grade: " + averageGrade.orElse(0));

        int multiplication = numbers.stream()
                .reduce(1, (a, b) -> a * b);
        System.out.println("14) Multiplication of all numbers: " + multiplication);

        long positiveCount = numbers.stream()
                .filter(n -> n > 0)
                .count();
        System.out.println("15) Count of positive numbers: " + positiveCount);

        System.out.println("\n========= Collectors & Grouping =========");

        Map<String, List<Student>> studentsByDepartment = students.stream()
                .collect(Collectors.groupingBy(Student::getDepartment));
        System.out.println("16) Students grouped by department: " + studentsByDepartment);

        Map<Boolean, List<Integer>> evenOddPartition = numbers.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println("17) Partition numbers into even/odd: " + evenOddPartition);

        String joinedNames = names.stream()
                .filter(Objects::nonNull)
                .filter(name -> !name.isEmpty())
                .collect(Collectors.joining(", "));
        System.out.println("18) Comma-separated names: " + joinedNames);

        Map<Integer, Long> employeesCountByAge = employees.stream()
                .collect(Collectors.groupingBy(Employee::getAge, Collectors.counting()));
        System.out.println("19) Employees count by age: " + employeesCountByAge);

        Map<String, Double> averageSalaryByDepartment = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ));
        System.out.println("20) Average salary by department: " + averageSalaryByDepartment);

        System.out.println("\n========= Optional, Map, FlatMap =========");

        List<String> flattenedWords = nestedWords.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        System.out.println("21) Flattened words: " + flattenedWords);

        List<String> uniqueCharacters = names.stream()
                .filter(Objects::nonNull)
                .filter(name -> !name.isEmpty())
                .flatMap(name -> name.chars().mapToObj(c -> String.valueOf((char) c)))
                .distinct()
                .collect(Collectors.toList());
        System.out.println("22) Unique characters: " + uniqueCharacters);

        List<String> nonEmptyOptionalValues = optionalNames.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        System.out.println("23) Non-empty Optional values: " + nonEmptyOptionalValues);

        List<Integer> nameLengths = names.stream()
                .filter(Objects::nonNull)
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println("24) Lengths of names: " + nameLengths);

        List<String> upperWordsStartingWithA = names.stream()
                .filter(Objects::nonNull)
                .filter(name -> name.startsWith("A"))
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("25) Uppercased names starting with A: " + upperWordsStartingWithA);

        System.out.println("\n========= Advanced Operations =========");

        List<Employee> sortedEmployees = employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary)
                        .thenComparing(Employee::getName))
                .collect(Collectors.toList());
        System.out.println("26) Employees sorted by salary then name: " + sortedEmployees);

        Optional<Integer> secondHighest = numbers.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst();
        System.out.println("27) Second highest number: " + secondHighest.orElse(null));

        Set<Integer> seen = new HashSet<>();
        Set<Integer> duplicates = numbers.stream()
                .filter(n -> !seen.add(n))
                .collect(Collectors.toSet());
        System.out.println("28) Duplicate numbers: " + duplicates);

        List<String> validNames = names.stream()
                .filter(Objects::nonNull)
                .filter(name -> !name.trim().isEmpty())
                .collect(Collectors.toList());
        System.out.println("29) Names after removing null/empty: " + validNames);

        Map<Boolean, List<Student>> passFailStudents = students.stream()
                .collect(Collectors.partitioningBy(student -> student.getGrade() >= 50));
        System.out.println("30) Students partitioned into pass/fail: " + passFailStudents);
    }
}