; 20.Написать функцию, упорядочивающую список, 
; заданный в качестве ее первого аргумента, 
; переставляя его элементы в той последовательности, 
; в какой они встречаются в списке, являющемся значением второго аргумента.

; f((1 2 2 3 4 5 6) (8 7 6 5 4 3 2 1)) => (6 5 4 3 2 2 1)


; Проверка наличия элекмента в списке.
(defun is-element-exist (data item)
    (cond 
        ((null data)
            nil) ; cond case 1 
        ((eq (car data) item)
            t) ; cond case 2
        (t 
            (is-element-exist (cdr data) item)) ; cond end case
    ) ; cond
) ; defun

; Подсчет дубликатов.
(defun count-of-duplicates (list el)
    (if list
        (if (eq (car list) el)
            (1+ (count-of-duplicates (cdr list) el)) ; if true
            (count-of-duplicates (cdr list) el) ; else
        )
        0
    ) ; if
) ; defun

; Генерирование n-ого кол-ва символов.
(defun gen-list(num count)
  (_gen-list num count))

(defun _gen-list (num count)
    (cond 
        ((<= count 0) nil)
        (t
            (cons num
                (_gen-list num (1- count))))
    ) ; cond
) ; defun

; Слияние листов в одномерный лист.
(defun concat-lists (list1 list2)
    (if (null list1)
        list2
        (cons (car list1) (concat-lists (cdr list1) list2))
    ) ; if
) ; defun


; Task.
(defun new-sort (raw-list order-list)
    (cond 
        ((or (null raw-list) (null order-list)) 
            nil) ; cond case 1
        
        ((is-element-exist raw-list (car order-list))
            (concat-lists 
                (gen-list (car order-list) (count-of-duplicates raw-list (car order-list))) 
                (new-sort raw-list (cdr order-list))
            ) ; concat-lists
        ) ; cond case 2

        (t 
            (new-sort raw-list (cdr order-list))) ; cond end case
    ) ; cond
) ; defun


(print (new-sort '(1 4 2 2 3 3 4 7 6 6 1) '(7 6 5 1 4 3 2)))
