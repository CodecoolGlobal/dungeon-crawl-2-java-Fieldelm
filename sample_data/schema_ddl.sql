ALTER TABLE IF EXISTS ONLY public.player
    DROP CONSTRAINT IF EXISTS fk_inventory_id;
DROP TABLE IF EXISTS public.inventory;

ALTER TABLE IF EXISTS ONLY public.game_state
    DROP CONSTRAINT IF EXISTS fk_player_id;
ALTER TABLE IF EXISTS ONLY public.map_items
    DROP CONSTRAINT IF EXISTS fk_map_id ;

DROP TABLE IF EXISTS public.game_state;
CREATE TABLE public.game_state (
        id serial NOT NULL PRIMARY KEY,
        current_map text NOT NULL,
        saved_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
        player_id integer NOT NULL
);

DROP TABLE IF EXISTS public.player;
CREATE TABLE public.player (
       id serial NOT NULL PRIMARY KEY,
       player_name text NOT NULL,
       hp integer NOT NULL,
       x integer NOT NULL,
       y integer NOT NULL
);

DROP TABLE IF EXISTS public.player_items;
CREATE TABLE public.player_items (
       id serial NOT NULL PRIMARY KEY,
       player_id integer NOT NULL,
       item_name text NOT NULL,
       item_quantity integer NOT NULL
);

DROP TABLE IF EXISTS public.map_items;
CREATE TABLE public.map_items (
      id serial NOT NULL PRIMARY KEY,
      map_id integer NOT NULL,
      item_name text NOT NULL,
      x integer NOT NULL,
      y integer NOT NULL
);

ALTER TABLE ONLY public.game_state
    ADD CONSTRAINT fk_player_id FOREIGN KEY (player_id) REFERENCES public.player(id);

ALTER TABLE ONLY public.player_items
    ADD CONSTRAINT fk_player_id FOREIGN KEY (player_id) REFERENCES public.player(id);

ALTER TABLE ONLY public.map_items
    ADD CONSTRAINT fk_map_id FOREIGN KEY (map_id) REFERENCES public.game_state(id);
