-- https://leetcode.com/problems/friday-purchases-ii/
-- recursive, cte, window, join

With recursive A as (
    select CAST('2023-11-01' as DATE) as dd
    union
    select DATE_ADD(dd, interval 1 day) as dd from A where dd < CAST('2023-11-30' as DATE)
),
B as (select WEEKOFYEAR(dd) as woy, DAYOFWEEK(dd) as dow, dd from A),
C as (select dense_rank() over(partition by null order by woy) as r_no, dd from B where dow = 6),
D as (select r_no as week_of_month, dd as purchase_date, SUM(COALESCE(amount_spend, 0)) as total_amount from C c left join Purchases p on c.dd = p.purchase_date group by r_no, dd)
select * from D;