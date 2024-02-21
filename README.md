# A RESTful API project using Spring framework

### I. Introduction

This project is the BE service for a web that is like Overflow. Technologies used: Spring frameword, MySQL. There are several features that are not complete or add such as: calculate contribution, sort type, accepted answer, tags,... Because of the absence of a frontend, this project must be run by Postman or a similar application.

### II. Features
#### Complete
- Register
- Post a question
- Comment in a post
- Upvote a post
- Upvote a comment
- Search post by keyword
#### In progress
- Add elasticsearch to improve searching performance
- Add tags to post
- Search by tags
- Accepted answer


### III. Installation

1. Clone the repo
```
https://github.com/nhatngohong/project.git
```

### IV. How To Use

Example for login
```

```



##### Create header file

With each data project, create a header file, which contains header name and file path template. Example:
```
$(dataroot)/DATA/FU_proj/data/${rg}/${YYYY}/${MM}/dataset_name.${YYYYMMDD}  
ii(bbid)|company_name|text|price|ratio
```

If a data project contain multiple dataset, can define them all in one header file. Example:
```
$(dataroot)/DATA/FU_proj/data/${rg}/${YYYY}/${MM}/dataset_1.${YYYYMMDD}  
ii(bbid)|company_name|text|price|ratio


$(dataroot)/DATA/FU_proj/data/${rg}/${YYYY}/${MM}/dataset_2.${YYYYMMDD}
ii(bbid)|news|new_sentiment|author|crawl_date
```

Each data set is combined by 2 lines:
- The first line is for the data path template, region wildcard and datetime wildcard should be as above.
- The second line is for the name of columns, separated by pipe character. If the data have one of the column which is a security identifier (should have), it must be named `ii` and follow by the type of the identifier with format `(name_of_identifier)`. For example: `ii(bbid)` or `ii(isin)`.
- Supported security identifiers:
```
ticker
cusip
sedol
bbid
isin
```

##### Run the tool

After created the header file. We can run the tool as example:
```
rb_gen \
    --data_root /home/your_name/ \
    --output_fp /path/to/FU_proj.rb \
    --header /path/to/header_file/FU_proj.header
```
Options:  
data_root (-d, --data_root): the root path contains the data project, this path will be used to replace `$(dataroot)` in the header file.  
output_fp (-p, --output_fp): the output file's path.  
header (-f, --header_specify_fp): the header file's path.  
limit (--sample_size): the number of data file that the tool will take as sample to determine data type of columns. Default is 100.  
delimiter (-m, --delimiter): the delimiter of the csv file. Default is `|`

##### Check the rb file

After the rb file has been generated, please manually check and handle to researcher to double check.

### V. Notes

- This tool does not (yet) support repeated data type, for example the tickers columns in the following table:
  ```
    tickers|value|crawl_date
    AAPL,NFLX,AMZ|0.1|20230101
  ```
  with this data type, please specify the data type in the rb file as:
  ```
    repeated 0, "tickers" do
        type string
        delim ","
    end
  ```
  or with repeated bbid:
  ```
    repeated 13, "ii"  do
      type bbid
      delim ","
    end
  ```
- With columns whose most of the value being null, the tool will not guess the data type but let user determine instead. See the column 1 in the below example:
```ruby
stddmgr!

namespace "FU_proj"

daily_data "proj" do
  delim "|"
  columns [3]
  datapath "$(dataroot)/DATA/FU_proj/data/${rg}/${YYYY}/${MM}/proj.${YYYYMMDD}"
  struct do
      bbid 0, "ii"
      # unknown 1, "unknown1" # Cannot determine data type, please fill data type manually
      datetime 2, "crawl_ts", "YYYYMMDD hhmmss"
  end
end
```