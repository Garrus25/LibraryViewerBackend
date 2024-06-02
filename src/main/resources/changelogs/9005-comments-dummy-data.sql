insert into comments (id, body, username, user_id, parent_id, created_at, book_id)
values
    (1, 'First comment', 'User1', 1, null, '2021-01-01 00:00:00', 9788368005066),
    (2, 'Second comment', 'User2', 2, null, '2021-01-01 00:00:00', 9788368005066),
    (3, 'First comment first child', 'User2', 2, 1, '2021-01-01 00:00:00', 9788368005066),
    (4, 'Second comment second child', 'User1', 2, 2, '2021-01-01 00:00:00', 9788368005066);