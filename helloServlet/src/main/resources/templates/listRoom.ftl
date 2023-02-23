<!DOCTYPE html>
<html lang="fr">
<meta charset="utf-8"/>
<head>
    <title>${title}</title>
</head>
<body>
<h2> list of FakeRooms</h2>
<p></p>
<ul>
    <#list fakeRooms as room>
        <li>
            ${room.name} : ${room.occupation} / ${room.capacity}
        </li>
    </#list>
</ul>
</body>
</html>