# Custom-DatePicker-DatePicker-Dialog

This is custom DatePicker & DatePickerDialog. It should be used for check certain date in calendar. I select 1-3 Junuary in example below. You can select any dates. 

![device-2017-02-08-123814](https://cloud.githubusercontent.com/assets/13554528/22727640/c16ed99c-edfb-11e6-8c26-4edc1508b258.png)

## Used example
* Used example
 * For show a graph of employment
  * For show an important date and etc
  
## How to use

### Java code

You can add programatically or specify in xml code
```java
 ArrayList<ArrayList<MyPair<Integer, Integer>>> Selected_interval = new ArrayList<>();
        Selected_interval.add(new ArrayList<MyPair<Integer, Integer>>());
        Selected_interval.get(Selected_interval.size() - 1).add(new MyPair<Integer, Integer>().mp(1, 3));
        //First dimensions is month second dimension is date interval for select. Example above select 1 - 3 day of 0 month(January)
        CustomDatePicker ds = (CustomDatePicker) findViewById(R.id.CustomDatePicker);
        ds.set_selected_interval(Selected_interval);
        ds.show_DatePickerDialog(2017);
        //show dialog
```

### xml code
```xml
 <com.example.myapplication.CustomDatePicker
        android:id="@+id/CustomDatePicker"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">
 </com.example.myapplication.CustomDatePicker>

```
